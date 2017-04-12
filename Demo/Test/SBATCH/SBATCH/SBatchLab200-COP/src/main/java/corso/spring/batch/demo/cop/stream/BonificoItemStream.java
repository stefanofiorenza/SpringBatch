package corso.spring.batch.demo.cop.stream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.stream.exceptions.BonificoMappingException;
import corso.spring.batch.demo.cop.stream.http.ConnectionKeepAliveStrategyImpl;
import corso.spring.batch.demo.cop.stream.http.HttpConfig;
import corso.spring.batch.demo.cop.stream.http.IdleConnectionMonitorThread;

@Slf4j
public class BonificoItemStream implements ItemStreamReader<List<BonificoDto>>{

	private CloseableHttpClient httpclient; 
	
	private int responseBufferSize;
	private int requestRetryMax=5;
	private long requestRetryIntervalMillis=10_000L;	
	private int cleanInterval=3000; //3 seconds
	
	@Autowired
	private HttpConfig httpConfig;
	
	
	@Override
	public void open(ExecutionContext executionContext)	throws ItemStreamException {
		
		log.info("BonificoItemStream configured with: "+httpConfig.toString());
		this.requestRetryMax=httpConfig.getRequestRetryMax();
		this.requestRetryIntervalMillis=httpConfig.getRequestRetryIntervalMillis();		
		this.responseBufferSize=httpConfig.getResponseBufferSize();	
		this.httpclient= createHttpClient(httpConfig);
		
	}

	@Override
	public List<BonificoDto> read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		HttpGet httpGetRequest = new HttpGet(httpConfig.getBonificoUri());
		return executeRequestOnServiceApi(httpGetRequest);			
	
	}
	
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
	
	
	
	}

	@Override
	public void close() throws ItemStreamException {
		try {
			this.httpclient.close();
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		
	}

	
	
	private List<BonificoDto> executeRequestOnServiceApi(HttpGet httpGetRequest, int reqCounter) throws ClientProtocolException, IOException, InterruptedException{
		
		CloseableHttpResponse httpResponse=null;
		
		try{
			
			
			log.info("Starting to receive response packets. Client Buffer size: {}",this.responseBufferSize);
			StopWatch stopWatch = new StopWatch();
			
			stopWatch.start();			
			httpResponse = executeHttpRequest(httpGetRequest, reqCounter);	
			stopWatch.stop();
			log.info("Response data completed after {} millis ",stopWatch.getTime());
						
			return mapIntoBonifico(httpResponse);			
			
		
			
		}catch (ConnectionPoolTimeoutException e){			
			handleConnectionTimeout(httpGetRequest, reqCounter);
			
		}	
		
		finally{			
			IOUtils.closeQuietly(httpResponse);			
		}
		
	}

	private List<BonificoDto> mapIntoBonifico(CloseableHttpResponse httpResponse) {
		List<BonificoDto> dtos = new ArrayList<BonificoDto>();
		
		if(httpResponse.getEntity()!=null){
			log.info("use httpResponse.getEntity().getContent() ... ");			
			return dtos;
		}else{
			BonificoMappingException ex= new BonificoMappingException("Server return unusable response");			
			throw ex;
		}
	}

	private CloseableHttpResponse executeHttpRequest(HttpGet httpGetRequest,
			int reqCounter) throws IOException, ClientProtocolException {
		CloseableHttpResponse httpResponse;
		log.info("Starting request to Uri: {} trial N {} ",httpGetRequest.getURI().toString(),reqCounter);			
		httpResponse = this.httpclient.execute(httpGetRequest);
		return httpResponse;
	}

	private void handleConnectionTimeout(HttpGet httpGetRequest, int reqCounter)
			throws InterruptedException, ClientProtocolException, IOException {
		log.warn("Request to {} went to timeout.Request will be aborted. New trial will be performed after {} millis", 
				httpGetRequest.getURI().toString(),this.requestRetryIntervalMillis );
		httpGetRequest.abort();			
		Thread.sleep(this.requestRetryIntervalMillis);
		
		reqCounter++;
		if(reqCounter<=this.requestRetryMax){
			executeRequestOnServiceApi(new HttpGet(httpGetRequest.getURI()), reqCounter);
		}else{
			throw new RuntimeException("Impossible to complete request to "+httpGetRequest.getURI()+
					" Number of trials ["+this.requestRetryMax+"] exceeded");
		}
	}
	
	private CloseableHttpClient createHttpClient(HttpConfig httpConfig ) {

		try {

			int idleConnTimeout = httpConfig.getConnectionTimeOutInMillis() + 1000;// 1 sec more than conn timeout
																				
			RequestConfig.Builder requestBuilder = RequestConfig.custom();

			ConnectionKeepAliveStrategyImpl keepAliveStrgy = new ConnectionKeepAliveStrategyImpl(idleConnTimeout);

			requestBuilder
					.setConnectTimeout(
							httpConfig.getConnectionTimeOutInMillis())
					.setSocketTimeout(httpConfig.getConnectionTimeOutInMillis())
					.setConnectionRequestTimeout(
							httpConfig.getRequestTimeOutInMillis());

			HttpClientBuilder builder = HttpClientBuilder.create();
			builder.setDefaultRequestConfig(requestBuilder.build());

			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setDefaultMaxPerRoute(httpConfig.getMaxThreadPerRoute());
			cm.setMaxTotal(httpConfig.getMaxConnPoolSize());
				
			
			IdleConnectionMonitorThread idleConnMonitor = new IdleConnectionMonitorThread(
					cm, this.cleanInterval, idleConnTimeout);
			idleConnMonitor.start();
			idleConnMonitor.join(1000);

			return builder.setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrgy).build();

		} catch (InterruptedException e) {// necessary for IdleConnMonitor
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}	    
	  }


	
	
	 
}

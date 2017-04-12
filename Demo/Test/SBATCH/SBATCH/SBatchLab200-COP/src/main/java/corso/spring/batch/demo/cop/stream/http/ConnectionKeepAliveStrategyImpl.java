package corso.spring.batch.demo.cop.stream.http;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

public class ConnectionKeepAliveStrategyImpl implements ConnectionKeepAliveStrategy{

	private int keepAliveInMillis;
	
	public ConnectionKeepAliveStrategyImpl(int keepAliveInMillis){
		this.keepAliveInMillis=keepAliveInMillis;
	}
	
	@Override
	public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
		
		HeaderElementIterator it = new BasicHeaderElementIterator(
				response.headerIterator(HTTP.CONN_KEEP_ALIVE));
		while (it.hasNext()) {
			HeaderElement he = it.nextElement();
			String param = he.getName();
			String value = he.getValue();

			if (value != null && param.equalsIgnoreCase("timeout")) {
				return Long.parseLong(value) * 1000;
			}
		}
		return this.keepAliveInMillis;
	}

}

package corso.spring.batch.demo.cop.stream.http;

import lombok.Data;

@Data
public class HttpConfig {

	private int maxThreadPerRoute=15;
	private int maxConnPoolSize=20;	
	private int connectionTimeOutInMillis=5_000;
	private int requestTimeOutInMillis=5_000;
	private int responseBufferSize=1024;
	private int requestRetryMax=5;
	private long requestRetryIntervalMillis=10_000L;
	private String bonificoUri="https://www.mockaroo.com/0ee2b7a0/download?count=1000&key=5e0b62d0";
}

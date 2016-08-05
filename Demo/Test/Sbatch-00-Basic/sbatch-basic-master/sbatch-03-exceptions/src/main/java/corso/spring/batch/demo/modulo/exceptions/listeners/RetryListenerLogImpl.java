package corso.spring.batch.demo.modulo.exceptions.listeners;

import org.apache.log4j.Logger;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

public class RetryListenerLogImpl extends RetryListenerSupport{

	
	final static Logger log = Logger.getLogger(RetryListenerLogImpl.class);

	@Override
	public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
		log.info("retried operation. Context RetryCount: "+context.getRetryCount());
	}
}

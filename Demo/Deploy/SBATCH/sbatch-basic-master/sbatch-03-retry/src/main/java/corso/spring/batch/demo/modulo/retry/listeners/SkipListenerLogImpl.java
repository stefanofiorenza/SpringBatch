package corso.spring.batch.demo.modulo.retry.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.SkipListener;

public class SkipListenerLogImpl implements SkipListener<Integer, Integer>{

	final static Logger log = Logger.getLogger(SkipListenerLogImpl.class);

	@Override
	public void onSkipInRead(Throwable t) {
		log.info("Not possible to complete read because of "+t.getMessage(),t);
	}

	@Override
	public void onSkipInWrite(Integer item, Throwable t) {
		
		log.info("Not possible to complete Write for item: "+item+" because of "+t.getMessage());
	}

	@Override
	public void onSkipInProcess(Integer item, Throwable t) {
		log.info("Not possible to complete Process for item: "+item+" because of "+t.getMessage());
	}

	
	
}

package corso.spring.batch.demo.modulo.exceptions.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemProcessListener;

public class ItemProcessListenerLogImpl implements ItemProcessListener<Integer, Integer>{

	final static Logger log = Logger.getLogger(ItemProcessListenerLogImpl.class);

	@Override
	public void beforeProcess(Integer item) {
		log.info("Before Processing: read item: "+item);
		
	}

	@Override
	public void afterProcess(Integer item, Integer result) {
		log.info("afterProcess. Received item: "+item+" Processed into: "+result);
		
	}

	@Override
	public void onProcessError(Integer item, Exception e) {
		log.info("Not possible to complete Process for item: "+item+" because of "+e.getMessage(),e);
	}

}

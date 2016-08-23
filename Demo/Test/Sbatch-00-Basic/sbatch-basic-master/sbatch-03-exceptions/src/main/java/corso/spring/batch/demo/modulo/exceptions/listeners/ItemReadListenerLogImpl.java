package corso.spring.batch.demo.modulo.exceptions.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemReadListener;

public class ItemReadListenerLogImpl implements ItemReadListener<Integer>{

	final static Logger log = Logger.getLogger(ItemReadListenerLogImpl.class);

	@Override
	public void beforeRead() {
		log.info("beforeRead");
		
	}

	@Override
	public void afterRead(Integer item) {
		log.info("afterRead: read item: "+item);
		
	}

	@Override
	public void onReadError(Exception ex) {
		log.info("Not possible to complete read because of "+ex.getMessage(),ex);
	}

}

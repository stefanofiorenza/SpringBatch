package corso.spring.batch.demo.modulo.retry.listeners;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;

public class ItemWriteListenerLogImpl implements ItemWriteListener<Integer>{

	final static Logger log = Logger.getLogger(ItemWriteListenerLogImpl.class);

	@Override
	public void beforeWrite(List<? extends Integer> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWrite(List<? extends Integer> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWriteError(Exception e, List<? extends Integer> items) {
		log.info("Not possible to complete Process for item: "+items.toString()+" because of "+e.getMessage(),e);
	}

}

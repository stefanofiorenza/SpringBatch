package corso.spring.batch.demo.modulo.basic.listeners;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;

import corso.spring.batch.demo.modulo.basic.job.io.model.User;

public class ItemWriteListenerLogImpl implements ItemWriteListener<User>{

	final static Logger log = Logger.getLogger(ItemWriteListenerLogImpl.class);

	@Override
	public void beforeWrite(List<? extends User> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWrite(List<? extends User> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends User> items) {
		// TODO Auto-generated method stub
		
	}

}

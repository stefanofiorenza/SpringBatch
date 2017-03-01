package corso.spring.batch.demo.modulo.basic.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemReadListener;

import corso.spring.batch.demo.modulo.basic.job.io.model.User;

public class ItemReadListenerLogImpl implements ItemReadListener<User>{

	final static Logger log = Logger.getLogger(ItemReadListenerLogImpl.class);

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRead(User item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReadError(Exception ex) {
		// TODO Auto-generated method stub
		
	}

}

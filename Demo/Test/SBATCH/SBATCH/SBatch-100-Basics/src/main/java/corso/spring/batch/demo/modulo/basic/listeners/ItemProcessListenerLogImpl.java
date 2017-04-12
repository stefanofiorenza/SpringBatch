package corso.spring.batch.demo.modulo.basic.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemProcessListener;

import corso.spring.batch.demo.modulo.basic.job.io.model.User;

public class ItemProcessListenerLogImpl implements ItemProcessListener<User, User>{

	final static Logger log = Logger.getLogger(ItemProcessListenerLogImpl.class);

	@Override
	public void beforeProcess(User item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterProcess(User item, User result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProcessError(User item, Exception e) {
		// TODO Auto-generated method stub
		
	}

}

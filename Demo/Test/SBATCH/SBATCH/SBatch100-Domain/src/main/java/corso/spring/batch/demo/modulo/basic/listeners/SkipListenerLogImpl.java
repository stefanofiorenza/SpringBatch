package corso.spring.batch.demo.modulo.basic.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.SkipListener;

import corso.spring.batch.demo.modulo.basic.job.io.model.User;

public class SkipListenerLogImpl implements SkipListener<User, User>{

	final static Logger log = Logger.getLogger(SkipListenerLogImpl.class);

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInWrite(User item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(User item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	
	
}

package corso.spring.batch.demo.modulo.basic.job.io;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.modulo.basic.job.io.model.User;
import corso.spring.batch.demo.modulo.basic.listeners.StepExecutionListenerLogImpl;



public class UserWriter implements ItemWriter<User>{

	final static Logger log = Logger.getLogger(UserWriter.class);

	
	@Override
	public void write(List<? extends User> utenti) throws Exception {
		
		log.info("Utenti da salvare in transazione:");
		
		//ExceptionDemoUtils.throwSkippableException("Eccezione nel Writer");
		
		for (User utente : utenti){
			log.debug(utente);
		}
		
		log.info("committed "+utenti.size()+" records");
		
	}

	
	
	
	

}

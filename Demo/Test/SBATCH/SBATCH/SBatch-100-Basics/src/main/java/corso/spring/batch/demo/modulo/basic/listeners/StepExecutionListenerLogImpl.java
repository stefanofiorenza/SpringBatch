package corso.spring.batch.demo.modulo.basic.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepExecutionListenerLogImpl implements StepExecutionListener{

	final static Logger log = Logger.getLogger(StepExecutionListenerLogImpl.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("beforeStep Execution");
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		log.info("After Step Execution");
		return stepExecution.getExitStatus();
	}



}

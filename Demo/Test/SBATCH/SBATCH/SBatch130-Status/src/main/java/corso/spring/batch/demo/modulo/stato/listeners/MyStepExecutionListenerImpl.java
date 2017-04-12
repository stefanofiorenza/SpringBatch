package corso.spring.batch.demo.modulo.stato.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;

import corso.spring.batch.demo.modulo.stato.job.service.NumberService;
import corso.spring.batch.demo.modulo.stato.job.utils.Consts;

public class MyStepExecutionListenerImpl implements StepExecutionListener{

	final static Logger log = Logger.getLogger(MyStepExecutionListenerImpl.class);


	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		
		
		return stepExecution.getExitStatus();
	}

	

	



}

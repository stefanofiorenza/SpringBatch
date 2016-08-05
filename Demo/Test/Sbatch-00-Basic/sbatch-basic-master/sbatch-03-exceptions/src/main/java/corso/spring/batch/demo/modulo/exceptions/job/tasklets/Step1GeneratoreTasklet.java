package corso.spring.batch.demo.modulo.exceptions.job.tasklets;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.modulo.exceptions.job.service.NumberService;
import corso.spring.batch.demo.modulo.exceptions.job.service.NumberServiceImpl;
import corso.spring.batch.demo.modulo.exceptions.launcher.Consts;

public class Step1GeneratoreTasklet implements Tasklet{
	
	final static Logger log = Logger.getLogger(Step1GeneratoreTasklet.class);

	private int failThreshold=95;
	
	private NumberService numberService;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
		
		Integer numberToSave=numberService.generateNumber();
		log.info("Generated: "+numberToSave.intValue());
			
		StepExecution stepExecution=chunkContext.getStepContext().getStepExecution();
		saveInJobExecutionContext(stepExecution,numberToSave);
		return RepeatStatus.FINISHED;
	}
	
	
	private void saveInJobExecutionContext(StepExecution stepExecution, Integer valueToSave){
		
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobExecutionCtx=jobExecution.getExecutionContext();
		jobExecutionCtx.put(Consts.VALUE_KEY, valueToSave);	
	}
	
	public NumberService getNumberService() {
		return numberService;
	}


	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}

}

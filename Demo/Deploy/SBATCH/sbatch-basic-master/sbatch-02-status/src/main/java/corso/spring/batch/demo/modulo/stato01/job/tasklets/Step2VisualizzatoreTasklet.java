package corso.spring.batch.demo.modulo.stato01.job.tasklets;



import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.modulo.stato.launcher.Consts;

public class Step2VisualizzatoreTasklet implements Tasklet{

	final static Logger log = Logger.getLogger(Step2VisualizzatoreTasklet.class);

	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext)
			throws Exception {

		StepExecution stepExecution=chunkContext.getStepContext().getStepExecution();
		JobExecution jobExecution = stepExecution.getJobExecution();
		
		Integer generated=(Integer)jobExecution.getExecutionContext().get(Consts.VALUE_KEY);
		log.info("[Visualizzatore]: Found: "+generated);
		return RepeatStatus.FINISHED;
	}
	
	

}

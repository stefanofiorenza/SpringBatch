package corso.spring.batch.demo.modulo.stato01.job.tasklets;



import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.modulo.stato.job.utils.Consts;

public class Step2VisualizzatoreTasklet implements Tasklet{

	final static Logger log = Logger.getLogger(Step2VisualizzatoreTasklet.class);

	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext)
			throws Exception {

		//1) read From Step Level
		//Integer generated= (Integer)chunkContext.getStepContext().getStepExecutionContext().get(Consts.VALUE_KEY);
						
		//2) read From in Job Level
		StepExecution stepExecution=chunkContext.getStepContext().getStepExecution();
		Integer generated= readFromJobExecutionContext(stepExecution);
				
		log.info("[Visualizzatore]: Found: "+generated);
		return RepeatStatus.FINISHED;
	}
	
	private Integer readFromJobExecutionContext(StepExecution stepExecution){
		
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobExecutionCtx=jobExecution.getExecutionContext();
		return (Integer)jobExecutionCtx.get(Consts.VALUE_KEY);	
	}

}

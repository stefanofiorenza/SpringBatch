package corso.spring.batch.demo.modulo.stato02.job.beanholder.tasklets;



import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.modulo.stato.job.service.BeanDataHolder;
import corso.spring.batch.demo.modulo.stato.launcher.Consts;

public class Step2VisualizzatoreTasklet implements Tasklet{

	final static Logger log = Logger.getLogger(Step2VisualizzatoreTasklet.class);
	private BeanDataHolder beanDataHolder;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext)
			throws Exception {

		
		Integer generated=beanDataHolder.getLargeDataCollection().get(Consts.VALUE_KEY);
		log.info("[Visualizzatore]: Found: "+generated);
		return RepeatStatus.FINISHED;
	}
	
	public BeanDataHolder getBeanDataHolder() {
		return beanDataHolder;
	}

	public void setBeanDataHolder(BeanDataHolder beanDataHolder) {
		this.beanDataHolder = beanDataHolder;
	}

}

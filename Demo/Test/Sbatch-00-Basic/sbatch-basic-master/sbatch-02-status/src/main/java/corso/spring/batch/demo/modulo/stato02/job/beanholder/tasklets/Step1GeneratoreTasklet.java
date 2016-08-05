package corso.spring.batch.demo.modulo.stato02.job.beanholder.tasklets;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.modulo.stato.job.service.BeanDataHolder;
import corso.spring.batch.demo.modulo.stato.job.service.NumberService;
import corso.spring.batch.demo.modulo.stato.launcher.Consts;

public class Step1GeneratoreTasklet implements Tasklet{
	
	final static Logger log = Logger.getLogger(Step1GeneratoreTasklet.class);

	
	private NumberService numberService;
	private BeanDataHolder beanDataHolder;
	


	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
		
		Integer numberToSave=numberService.generateNumber();
		log.info("Generated: "+numberToSave.intValue());		
		
		beanDataHolder.getLargeDataCollection().put(Consts.VALUE_KEY, numberToSave);
		return RepeatStatus.FINISHED;
	}
	
	

	
	public NumberService getNumberService() {
		return numberService;
	}


	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}
	
	public BeanDataHolder getBeanDataHolder() {
		return beanDataHolder;
	}

	public void setBeanDataHolder(BeanDataHolder beanDataHolder) {
		this.beanDataHolder = beanDataHolder;
	}

}

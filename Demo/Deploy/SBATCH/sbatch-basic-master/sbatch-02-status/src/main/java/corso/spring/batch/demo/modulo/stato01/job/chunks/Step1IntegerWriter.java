package corso.spring.batch.demo.modulo.stato01.job.chunks;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.modulo.stato.launcher.Consts;



public class Step1IntegerWriter implements ItemWriter<Integer>{

	final static Logger log = Logger.getLogger(Step1IntegerWriter.class);
	private StepExecution stepExecution;
	
	
	@BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

	
	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
		
		log.info("numbers da salvare in transazione: "+numbers.toString());
		
		Integer lastProcessed=null;
		
		for (Integer integer : numbers){
			log.debug(integer);	
			lastProcessed=new Integer(integer.intValue());
		}
		
		saveInJobExecutionContext(lastProcessed);		
		log.info("committed "+numbers.size()+" records");
		
	}

	private void saveInJobExecutionContext(Integer valueToSave){
		
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobExecutionCtx=jobExecution.getExecutionContext();
		jobExecutionCtx.put(Consts.VALUE_KEY, valueToSave);	
	}
	
	
	
	

}

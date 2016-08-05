package corso.spring.batch.demo.modulo.retry.job.chuncks;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.modulo.retry.exceptions.RetryThresholdException;
import corso.spring.batch.demo.modulo.retry.exceptions.SkipThresholdException;



public class IntegerWriter implements ItemWriter<Integer>{

	final private Random random= new Random();
	
	final static Logger log = Logger.getLogger(IntegerWriter.class);
	private int skipThresHold=60;
	private int retryFactor=5;	
	
	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
		
		log.info("numbers da salvare in transazione: "+numbers.toString());
		
		for (Integer integer : numbers){
			int value=integer.intValue();
			evaluateSkippable(value);
			evaluateRetriable(value);			
			log.debug(integer);			
		}		
		log.info("committed "+numbers.size()+" records");
		
	}

	
	private void evaluateSkippable(int value){
		int rndInt=random.nextInt(100)+1;
		if(rndInt<skipThresHold){
			throw new SkipThresholdException("Cannot Write on Log table. Record "+value+" Will be skipped");
		}	
	}
	
	private void evaluateRetriable(int recordValue){
		
		int rndInt=random.nextInt(100)+1;
		if(rndInt%retryFactor==0){
			throw new SkipThresholdException("Cannot Write on Data table. Table is locked . New update for Record "+recordValue+" will be retried");
		}			
	}
	
	
	
	

}

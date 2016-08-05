package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.modulo.exceptions.RetryThresholdException;



public class IntegerWriter implements ItemWriter<Integer>{

	final static Logger log = Logger.getLogger(IntegerWriter.class);
	private int retryFactor=2;	
	
	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
		
		log.info("numbers da salvare in transazione: "+numbers.toString());
		
		for (Integer integer : numbers){
			int value=integer.intValue();
			if(value%retryFactor==0){
				throw new RetryThresholdException("Generated: "+value+" was divisible for RETRY value: "+retryFactor);
			}
			log.debug(integer);			
		}
		
		log.info("committed "+numbers.size()+" records");
		
	}

	
	
	
	

}

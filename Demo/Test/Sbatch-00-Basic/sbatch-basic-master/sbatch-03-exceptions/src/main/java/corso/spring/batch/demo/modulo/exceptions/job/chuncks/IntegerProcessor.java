package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.modulo.exceptions.RetryThresholdException;


public class IntegerProcessor  implements ItemProcessor<Integer, Integer>{

	final static Logger log = Logger.getLogger(IntegerProcessor.class);
	private int coefficiente=1;
	private int retryFactor=2;	
	
	@Override
	public Integer process(Integer record) throws Exception {

		int recordValue=record.intValue();
		log.info("Ricevuto: "+record.intValue());
		
		recordValue=coefficiente+recordValue;
		log.info("Applicando coefficiente: "+coefficiente +" Nuovo valore: "+recordValue);
		
		
//		if(recordValue%retryFactor==0){
//			throw new RetryThresholdException("Generated: "+recordValue+" was divisible for RETRY value: "+retryFactor);
//		}
		
		return new Integer(recordValue);
	}

}

package corso.spring.batch.demo.modulo.retry.job.chuncks;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.modulo.retry.exceptions.RetryThresholdException;
import corso.spring.batch.demo.modulo.retry.exceptions.SkipThresholdException;


public class IntegerProcessor  implements ItemProcessor<Integer, Integer>{

	final static Logger log = Logger.getLogger(IntegerProcessor.class);
	private int coefficiente=1;
	private int skipThresHold=5;
	private int retryFactor=80;	
	final private Random random= new Random();
	
	
	@Override
	public Integer process(Integer record) throws Exception {

		int recordValue=record.intValue();
		log.info("Ricevuto: "+record.intValue());
		
		recordValue=coefficiente+recordValue;
		log.info("Applicando coefficiente: "+coefficiente +" Nuovo valore: "+recordValue);
		
		evaluateSkippable(recordValue);
		evaluateRetriable(recordValue);
		
		return new Integer(recordValue);
	}
	
	private void evaluateSkippable(int recordValue){
		int rndGen=random.nextInt();
		log.debug("Processor rnd: "+rndGen+" skipThresHold: "+skipThresHold);
		if(rndGen<skipThresHold){
			throw new SkipThresholdException("Cannot process : "+recordValue+" Record will be skipped");
		}	
	}
	
	private void evaluateRetriable(int recordValue){
		int rndGen=random.nextInt();
		log.debug("Processor rnd: "+rndGen+" retryFactor: "+retryFactor);
		if(rndGen%retryFactor==0){
			throw new RetryThresholdException("Cannot process : "+recordValue+" A new trial will be performed");
		}		
	}

}

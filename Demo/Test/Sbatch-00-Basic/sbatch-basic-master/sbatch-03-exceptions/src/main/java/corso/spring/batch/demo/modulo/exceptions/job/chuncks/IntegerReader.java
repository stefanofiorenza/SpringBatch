package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.modulo.exceptions.RetryThresholdException;
import corso.spring.batch.demo.modulo.exceptions.SkipThresholdException;
import corso.spring.batch.demo.modulo.exceptions.job.service.NumberService;


public class IntegerReader implements ItemReader<Integer>{

	final static Logger log = Logger.getLogger(IntegerReader.class);
	private int skipThresHold=10;
	private int retryFactor=10;	
	private static int contatore=0;	
	private NumberService numberService;
	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		
		contatore++;
		if (contatore==101){
			return null;
		}
		
		Integer numberToSave=numberService.generateNumber();
		if(numberToSave%retryFactor==0){
			throw new RetryThresholdException("Generated: "+numberToSave+" was divisible for RETRY value: "+retryFactor);
		}
	
		if(numberToSave<skipThresHold){
			throw new SkipThresholdException("Generated: "+numberToSave+" was lower than SKIP threshold: "+skipThresHold);
		}
		
		log.info("Contatore: "+contatore+" Generated: "+numberToSave.intValue());			
		return numberToSave;
		
	}

	
	public NumberService getNumberService() {
		return numberService;
	}


	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}

	public int getSkipThresHold() {
		return skipThresHold;
	}

	public void setSkipThresHold(int skipThresHold) {
		this.skipThresHold = skipThresHold;
	}

	public int getRetryFactor() {
		return retryFactor;
	}

	public void setRetryFactor(int retryThresHold) {
		this.retryFactor = retryThresHold;
	}
}

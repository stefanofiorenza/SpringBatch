package corso.spring.batch.demo.modulo.retry.job.chuncks;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.modulo.retry.exceptions.RetryThresholdException;
import corso.spring.batch.demo.modulo.retry.exceptions.SkipThresholdException;
import corso.spring.batch.demo.modulo.retry.job.service.NumberService;


public class IntegerReader implements ItemReader<Integer>{

	final static Logger log = Logger.getLogger(IntegerReader.class);
	private int skipThresHold=10;
	private int retryFactor=90;	
	private static int contatore=0;	
	final private Random random= new Random();
	private NumberService numberService;
	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		
		contatore++;
		if (contatore==101){
			return null;
		}
		
		Integer numberToSave=numberService.generateNumber();
		evaluateSkippable(numberToSave.intValue());
		evaluateRetriable(numberToSave.intValue());
		
		log.info("Contatore: "+contatore+" Generated: "+numberToSave.intValue());			
		return numberToSave;
		
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

package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.common.exceptions.SkipThresholdException;
import corso.spring.batch.demo.modulo.exceptions.job.AbstractChunkOrientedDemo;
import corso.spring.batch.demo.modulo.exceptions.job.service.NumberService;


public class IntegerReader extends AbstractChunkOrientedDemo  implements ItemReader<Integer>{

			
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		
		log.info("READER Summary: "+this.stepExecution.getSummary());	
				
		Integer numberToSave=numberService.produceNumber();
		log.info(" Retrieved: "+numberToSave);	
		
		//executeDangerousOperation(numberToSave);		
		numberService.ackNumber();
		
		return numberToSave;
		
	}

	final static Logger log = Logger.getLogger(IntegerReader.class);
	
	private NumberService numberService;
		
	public NumberService getNumberService() {
		return numberService;
	}

	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}




	
}

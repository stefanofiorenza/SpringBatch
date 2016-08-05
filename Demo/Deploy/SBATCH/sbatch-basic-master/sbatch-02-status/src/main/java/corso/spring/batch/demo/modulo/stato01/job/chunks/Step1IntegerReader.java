package corso.spring.batch.demo.modulo.stato01.job.chunks;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.modulo.stato.job.service.NumberService;
import corso.spring.batch.demo.modulo.stato.launcher.Consts;



public class Step1IntegerReader implements ItemReader<Integer>{


	private static int contatore=0;
	
	final static Logger log = Logger.getLogger(Step1IntegerReader.class);
		
	
	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		
		contatore++;
		if (contatore==101){
			return null;
		}
		
		Integer numberToSave=numberService.generateNumber();
		log.info("Generated: "+numberToSave.intValue());					
		return numberToSave;
		
	}



	
	private NumberService numberService;
	
	public NumberService getNumberService() {
		return numberService;
	}


	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}

}

package corso.spring.batch.demo.modulo.stato01.job.chunks;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.modulo.stato.job.service.NumberService;
import corso.spring.batch.demo.modulo.stato.launcher.Consts;



public class Step2IntegerReader implements ItemReader<Integer>{


	private static int contatore=0;
	
	final static Logger log = Logger.getLogger(Step2IntegerReader.class);		
	private StepExecution stepExecution;
	
	
	@BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
	
	
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		
		contatore++;
		if (contatore==101){
			return null;
		}
		
		JobExecution jobExecution = stepExecution.getJobExecution();		
		Integer lastGenerated=(Integer)jobExecution.getExecutionContext().get(Consts.VALUE_KEY);
		log.info("Last From Step1: "+new Integer(lastGenerated));	
		
		int newGeneratedInt=numberService.generateNumber().intValue();
		log.info("Generated From Service: "+new Integer(newGeneratedInt));	
		
		int numberToSave=lastGenerated.intValue()+newGeneratedInt;
		log.info("Step2 Reader Value: "+new Integer(numberToSave));					
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

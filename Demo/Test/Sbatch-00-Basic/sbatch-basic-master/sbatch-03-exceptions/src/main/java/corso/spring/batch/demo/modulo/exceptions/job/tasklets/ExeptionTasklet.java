package corso.spring.batch.demo.modulo.exceptions.job.tasklets;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.modulo.exceptions.RetryFactorException;
import corso.spring.batch.demo.modulo.exceptions.SkipThresholdException;
import corso.spring.batch.demo.modulo.exceptions.job.AbstractDemoException;
import corso.spring.batch.demo.modulo.exceptions.job.service.NumberService;

public class ExeptionTasklet extends AbstractDemoException implements Tasklet{
	
	final static Logger log = Logger.getLogger(ExeptionTasklet.class);

	private NumberService numberService;
	private final int COUNTER=100;
	
	private final int COMMIT_INTERVAL=10;
	
	private StepExecution currStepExecution;
	
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
		
		
		currStepExecution=chunkContext.getStepContext().getStepExecution();
		
		Integer processed;		
		do {
			processed=executeChunk();
		}while(processed!=null);
		
		return RepeatStatus.FINISHED;
	}
	
	
	private Integer executeChunk(){
			
		Integer read=0;
		List<Integer> itemsToWrite;
		List<Integer> chunkOfItems= new ArrayList<>();
	
		//1) read chunk
		for (int j=0; j<COMMIT_INTERVAL; j++){			
				read=read();				
				if(read==null){
					return null;
				}
				chunkOfItems.add(read);					
		}
		
		//2) process chunk
		itemsToWrite=process(chunkOfItems);
		
		//3) write chunk
		write(itemsToWrite);
	
		return itemsToWrite.size();
		
	}
	
	private Integer read(){		
		log.info("READER Summary: "+currStepExecution.getSummary());
		
		Integer numberToSave=numberService.produceNumber();
		
		log.info("Retrieved: "+numberToSave);		
		//executeDangerousOperation(numberToSave);
		
		if(numberToSave!=null){
			numberService.ackNumber();			
		}
		
		return numberToSave;
	}
	
	
	private List<Integer> process(List<Integer> list){
		
	
		List<Integer> newList= new ArrayList<>();		
		for (Integer item : list){
			newList.add(processSingle(item));
		}
		return newList;		
	}
	
	private void write(List<Integer> numbers){
		
		log.info("numbers da salvare in transazione: "+numbers.toString());
			
		log.info("WRITER Summary: "+currStepExecution.getSummary());	
		
		for (Integer integer : numbers){
			
			//1) commenta/decommenta per ex in Writer
			//executeDangerousOperation(integer);				
			log.debug("Write: "+integer);			
		}
		log.info("committed "+numbers.size()+" records");
		
	}
	
	
	/** simply copy it */
	private Integer processSingle (Integer record){
		
		log.info("PROCESSOR Summary: "+currStepExecution.getSummary());
		int recordValue=record.intValue();		
		log.info("Ricevuto: "+recordValue);	
		
		recordValue++;
		log.info("After Processor copiato valore: "+recordValue);
		
		// commenta/decommenta per ex in Processor
		//executeDangerouseOperation(recordValue);	
		
		return new Integer(recordValue);
	}
	
	

	
	
	public NumberService getNumberService() {
		return numberService;
	}


	public void setNumberService(NumberService numberService) {
		this.numberService = numberService;
	}

}

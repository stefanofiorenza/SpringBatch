package corso.spring.batch.demo.modulo.exceptions.job.tasklets;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.skip.SkipPolicyFailedException;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.common.exceptions.SkipThresholdException;
import corso.spring.batch.demo.modulo.exceptions.job.AbstractChunkOrientedDemo;
import corso.spring.batch.demo.modulo.exceptions.job.service.NumberService;

public class ExeptionTasklet extends AbstractChunkOrientedDemo implements Tasklet{
	
	final static Logger log = Logger.getLogger(ExeptionTasklet.class);

	private NumberService numberService;
	private final int COUNTER=100;
	
	private final int COMMIT_INTERVAL=10;	
	private final int RETRY_LIMIT=5;
	private final int SKIP_LIMIT=10;
	
	private int retrial=0;
	private int skipped=0;
	private StepExecution currStepExecution;
	
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
			throws Exception {
		
		
		currStepExecution=chunkContext.getStepContext().getStepExecution();
		
		Integer processed;		
		do {
			try{
				processed=executeChunk();
			}catch (Exception e){
				return RepeatStatus.FINISHED;
			}
		}while(processed!=null);
		
		return RepeatStatus.FINISHED;
	}
	
	
	private Integer executeChunk(){
			
		
		//1) read chunks
		List<Integer> chunkOfItems= readChunk();
			
		//2) process chunk
		List<Integer> itemsToWrite=process(chunkOfItems);
		
		//3) write chunk
		write(itemsToWrite);
	
		return itemsToWrite.size();
		
	}
	
	private List<Integer> readChunk(){
		Integer read=0;
		List<Integer> chunkOfItems= new ArrayList<>();
				
		//1) read chunk
		for (int j=0; j<COMMIT_INTERVAL; j++){	
				try{
					read=read();			
					
				}catch (RetryFactorException e){
					retrial++;
					if(retrial<RETRY_LIMIT){
						read=read();
					}else{
						throw new SkipThresholdException("Retry limit exceeded");//not an error, should be skippable
					}					
				}catch (SkipThresholdException e){
					skipped++;
					if(skipped<SKIP_LIMIT){
						continue;
					}else{
						throw new RuntimeException("Skip limit exceeded");
					}					
				}
				
				if(read==null){
					return null;
				}
				chunkOfItems.add(read);	
								
		}
		
		return chunkOfItems;
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
		Integer processed;
		for (Integer item : list){
			try{
				processed=processSingle(item);	
				
			}catch (RetryFactorException e){
				retrial++;
				if(retrial<RETRY_LIMIT){
					processed=processSingle(item);
				}else{
					throw new SkipThresholdException("Retry limit exceeded");//not an error, should be skippable
				}					
			}catch (SkipThresholdException e){
				skipped++;
				if(skipped<SKIP_LIMIT){
					continue;
				}else{
					throw new RuntimeException("Skip limit exceeded");
				}
				
			}
			newList.add(processed);
		}
		return newList;		
	}
	
	private void write(List<Integer> numbers){
		
		log.info("numbers da salvare in transazione: "+numbers.toString());
			
		log.info("WRITER Summary: "+currStepExecution.getSummary());
						
		for (Integer item : numbers){
			
			try{
				writeSingle(item);		
				
			}catch (RetryFactorException e){
				retrial++;
				if(retrial<RETRY_LIMIT){
					writeSingle(item);		
				}else{
					throw new SkipThresholdException("Retry limit exceeded");//not an error, should be skippable
				}					
			}catch (SkipThresholdException e){
				skipped++;
				if(skipped<SKIP_LIMIT){
					continue;
				}else{
					throw new RuntimeException("Skip limit exceeded");
				}
				
			}
				
		}
		log.info("committed "+numbers.size()+" records");
		
	}
	
	private void writeSingle(Integer item){
		//1) commenta/decommenta per ex in Writer
		//executeDangerousOperation(integer);				
		log.debug("Write: "+item);
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

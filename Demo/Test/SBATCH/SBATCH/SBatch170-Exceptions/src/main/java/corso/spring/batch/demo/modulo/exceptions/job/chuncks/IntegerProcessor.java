package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.modulo.exceptions.job.AbstractChunkOrientedDemo;


public class IntegerProcessor extends AbstractChunkOrientedDemo 
	implements ItemProcessor<Integer, Integer> {

	final static Logger log = Logger.getLogger(IntegerProcessor.class);
		
	@Override
	public Integer process(Integer record) throws Exception {

		//log.info("PROCESSOR Summary: "+this.stepExecution.getSummary());
		
		int recordValue=increaseAndGetNewValue(record);	
			
		// commenta/decommenta per ex in Processor
		//executeDangerouseOperation(recordValue);	
				
		return new Integer(recordValue);
	}

	
	private int increaseAndGetNewValue(Integer record){
		
		int recordValue=record.intValue();		
		log.info("Ricevuto: "+recordValue);
		
		recordValue++;
		log.info("After Processor copiato valore: "+recordValue);	
		
		return recordValue;		
	}
		
}

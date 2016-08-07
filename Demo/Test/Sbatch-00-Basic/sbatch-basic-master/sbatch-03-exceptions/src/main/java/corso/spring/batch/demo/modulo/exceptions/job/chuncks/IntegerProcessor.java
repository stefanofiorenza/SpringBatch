package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.modulo.exceptions.RetryFactorException;


public class IntegerProcessor extends AbstractChunkProcessing 
	implements ItemProcessor<Integer, Integer> {

	final static Logger log = Logger.getLogger(IntegerProcessor.class);
		
	@Override
	public Integer process(Integer record) throws Exception {

		//log.info("PROCESSOR Summary: "+this.stepExecution.getSummary());
		
		int recordValue=record.intValue();		
		log.info("Ricevuto: "+recordValue);
		
		//int processedValue=recordValue+1;
		recordValue++;
		log.info("After Processor copiato valore: "+recordValue);	
		
		// commenta/decommenta per ex in Processor
		//executeDangerouseOperation(recordValue);	
		
		return new Integer(recordValue);
	}

	
	
	
}

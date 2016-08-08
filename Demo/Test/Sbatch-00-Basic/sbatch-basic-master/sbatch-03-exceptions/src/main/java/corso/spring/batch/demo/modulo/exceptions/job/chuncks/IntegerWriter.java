package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.modulo.exceptions.RetryFactorException;



public class IntegerWriter extends AbstractChunkProcessing 
								implements ItemWriter<Integer>{

	final static Logger log = Logger.getLogger(IntegerWriter.class);
		
	
	
	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
		
				
		log.info("numbers da salvare in transazione: "+numbers.toString());
		log.info("WRITER Summary: "+this.stepExecution.getSummary());	
		
		for (Integer integer : numbers){
			
			//1) commenta/decommenta per ex in Writer
			executeDangerousOperation(integer);	
			
			log.debug(integer);			
		}
		log.info("committed "+numbers.size()+" records");
		
	}

	

}

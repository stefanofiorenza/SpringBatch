package corso.spring.batch.demo.cop.stream.plain;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;


@Slf4j
public class BonificoItemStreamReaderBasicImpl implements ItemStreamReader<BonificoDto>{

	private static final int LAST_BONIFICO=51;	
	private static final int EXCEPTION_INDEX=45;
	
	//private static int executionCount=0;
	private static int readCounter;
	private static int lastSuccessfulReadIndex;
	private static int recordInException;
	
	private static String LAST_EXCEPTION_INDEX="LAST_EXCEPTION_INDEX";	
	private static String LAST_SUCCESSFUL_READ="LAST_SUCCESSFUL_READ";	
	
	
	/**
	 * called once on start
	 */
	@Override
	public void open(ExecutionContext executionContext)	throws ItemStreamException {
		
		log.info("In Reader OPEN");
//		Integer lastSuccRead=(Integer)executionContext.get(LAST_SUCCESSFUL_READ);
//		if(lastSuccRead!=null){
//			
//		}
//		updateExecutionContextWithCounters(executionContext);	
		
		
	}

	
	/**
	 * N read until commit interval or null
	 */
	@Override
	public BonificoDto read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
					
		
		readCounter++;
		BonificoDto bonifico= BonificoDtoUtils.createMockBonifico(readCounter);
		
		//ExceptionDemoUtils.evaluateExceptions(bonifico.getOrderId(), this.getClass());
		//evaluateDeterministicException(bonifico);
		
		if(readCounter==LAST_BONIFICO){
			return null;
		}
		
		lastSuccessfulReadIndex++;
		return bonifico;
		
	}
	
	
	
	/**
	 * called after every commit
	 */
	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		log.info("UPDATED Chunk");
		updateExecutionContextWithCounters(executionContext);			
	}

	private void updateExecutionContextWithCounters(
			ExecutionContext executionContext) {
		executionContext.put(LAST_EXCEPTION_INDEX,recordInException);
		executionContext.put(LAST_SUCCESSFUL_READ,lastSuccessfulReadIndex);
		
		log.info("Execution Context updated with values "+
				LAST_EXCEPTION_INDEX+": "+recordInException+" "+
				LAST_SUCCESSFUL_READ+": "+lastSuccessfulReadIndex);

		
	}


	private void evaluateDeterministicException(BonificoDto bonifico){
		if(readCounter==EXCEPTION_INDEX){							
			recordInException=readCounter;
			log.warn("Exception for Bonifico with OrderID: "+bonifico.getOrderId());
			throw new RetryFactorException("Bonifico with OrderID: "+bonifico.getOrderId()+
					" in exception on ReadCount: "+lastSuccessfulReadIndex+
					" And ExceptionIndex: "+EXCEPTION_INDEX);
		}
	}
	
	/**
	 * cleanup
	 */
	@Override
	public void close() throws ItemStreamException {
		log.info("Clean Execution Context in close method");	
		readCounter=0;
		recordInException=0;
		lastSuccessfulReadIndex=0;
	}

	
	
//	private void updateExecutionContextWithCounters(ExecutionContext executionContext){
//		//executionContext.put(EXECUTION_COUNT_KEY,executionCount);
//		executionContext.put(READ_COUNT_KEY,lastSuccessfulReadIndex);
//			
//		log.info("Execution Context updated with values "+
//				//EXECUTION_COUNT_KEY+": "+executionCount+" "+
//				READ_COUNT_KEY+": "+lastSuccessfulReadIndex);
//	}
	
	
	 
}

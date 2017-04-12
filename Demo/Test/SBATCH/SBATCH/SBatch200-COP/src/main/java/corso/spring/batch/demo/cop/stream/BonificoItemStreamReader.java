package corso.spring.batch.demo.cop.stream;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;


@Slf4j
public class BonificoItemStreamReader extends AbstractItemCountingItemStreamItemReader<BonificoDto>{

	
	private static final int LAST_BONIFICO=51;	
	private static final int EXCEPTION_INDEX=45;

	
	public BonificoItemStreamReader(){
		setName("BonificoItemStreamReader");
	}
	
	
	@Override
	protected void doOpen() throws Exception {		
		log.info("BonificoReader.open");		
		log.info("Last processed item: "+getCurrentItemCount());
	}
	

	@Override
	protected BonificoDto doRead() throws Exception {		

		int readCounter=getCurrentItemCount();
		BonificoDto bonifico= BonificoDtoUtils.createMockBonifico(readCounter);
		
		evaluateDeterministicException(bonifico);
		return bonifico;		
	}


	
	private void evaluateDeterministicException(BonificoDto bonifico){
		int currentItem=getCurrentItemCount();
		if(currentItem==EXCEPTION_INDEX){		
			log.warn("Exception for Bonifico with OrderID: "+bonifico.getOrderId());
			throw new RetryFactorException("Bonifico with OrderID: "+bonifico.getOrderId()+
					" in exception on ReadCount: "+currentItem+
					" And ExceptionIndex: "+EXCEPTION_INDEX);
		}
	}


	@Override
	protected void doClose() throws Exception {
		
//		setCurrentItemCount(0);
//		setMaxItemCount(100);
		log.info("BonificoReader.close.");
		
	}


	
	
	 
}

package corso.spring.batch.demo.cop.stream;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.commons.dto.BonificoDto;

@Slf4j
public class BonificoItemStreamWriter  implements ItemStreamWriter<BonificoDto>{
		
	private static int writeCounter;
	private static int lastSuccessfulWriteIndex;
	private static int recordInException;
	
	@Override
	public void open(ExecutionContext executionContext)
			throws ItemStreamException {
		log.info("In Writer Open executionContext: "+executionContext.toString());		
	}

	@Override
	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		log.info("In update executionContext: "+executionContext.toString());		
	}

	@Override
	public void close() throws ItemStreamException {
		log.info("Close");	
	}

	@Override
	public void write(List<? extends BonificoDto> items) throws Exception {
		for (BonificoDto bonifico : items){
			ExceptionDemoUtils.evaluateExceptions("Bonifico#"+bonifico.getOrderId(), this.getClass());
			log.info("Write Bonifico#"+bonifico.getOrderId());
		}
		
	}

	
	private void writeItem(BonificoDto bonifico){
		try{
			ExceptionDemoUtils.evaluateExceptions("Item ["+bonifico.getOrderId()+"]", this.getClass());
			log.info("Saved "+bonifico.getOrderId());
		} catch(Exception e){
			
		}
		
	}
}

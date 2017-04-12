package corso.spring.batch.demo.cop.stream.ext;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;

@Slf4j
public class BonificoItemStreamWriterDelegateImpl implements ItemStreamWriter<BonificoDto>{


		
	private FlatFileItemWriter<BonificoDto> delegate;
	
	public BonificoItemStreamWriterDelegateImpl(FlatFileItemWriter<BonificoDto> delegate){
		this.delegate=delegate;
	}
	
	
	@Override
	public void open(ExecutionContext executionContext)
			throws ItemStreamException {
		log.info("In Writer Open executionContext: "+executionContext.toString());
		delegate.open(executionContext);
		
	}

	@Override
	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		log.info("In update executionContext: "+executionContext.toString());
		delegate.update(executionContext);		
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();		
	}

	@Override
	public void write(List<? extends BonificoDto> items) throws Exception {		
		ExceptionDemoUtils.evaluateExceptions("Items ["+items.get(0).getOrderId()+"-"+items.get(4).getOrderId()+"]", this.getClass());
		delegate.write(items);	
		log.info("Data writer commit");
	}
	
	

}

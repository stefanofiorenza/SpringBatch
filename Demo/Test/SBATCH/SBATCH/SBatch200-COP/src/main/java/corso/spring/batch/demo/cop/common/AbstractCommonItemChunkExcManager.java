package corso.spring.batch.demo.cop.common;

import java.util.List;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.common.exceptions.NoRollbackException;
import corso.spring.batch.demo.commons.dto.BonificoDto;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractCommonItemChunkExcManager {

	@Getter
	@Setter
	private boolean evaluateExceptionRetryable=false;
	
	@Getter
	@Setter
	private boolean throwExceptionRollback=false;
	
	@Getter
	@Setter
	private boolean throwExceptionRuntime=false;
	
	protected void evaluateExceptions(BonificoDto item){
		
		if(evaluateExceptionRetryable){
			ExceptionDemoUtils.evaluateExceptions(item.getOrderId(), this.getClass());
		}
		
		if(throwExceptionRuntime){
			throw new RuntimeException("Exception in BonificoProcessor for record "+item.getOrderId());
		}
		
		if(throwExceptionRollback){
			throw new NoRollbackException("Exception in BonificoProcessor for record "+item.getOrderId());
		}
	}
	
	protected void evaluateExceptions(List<? extends BonificoDto> items){
		
		if(evaluateExceptionRetryable || throwExceptionRuntime || throwExceptionRollback){
			for (BonificoDto item: items){
				evaluateExceptions(item);
			}
		}
		
		
		
	}
}

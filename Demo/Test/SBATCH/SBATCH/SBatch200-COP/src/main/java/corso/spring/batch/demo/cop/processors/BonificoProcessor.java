package corso.spring.batch.demo.cop.processors;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;

@Slf4j
public class BonificoProcessor implements ItemProcessor<BonificoDto,BonificoDto>{

		
	@Override
	public BonificoDto process(BonificoDto item) throws Exception {
				
		//ExceptionDemoUtils.evaluateExceptions(item.getOrderId(), this.getClass());	
		
		
		//1) Fault tolerant copy of immutable input
		//return updateAndReturnNew(item);
				
		//2) No fault tolerant (flag processed)
		return updateAndReturnSame(item);	
				
		
	}

	private BonificoDto updateAndReturnNew(BonificoDto item) {
		return BonificoDtoUtils.updateAndReturnNew(item);		
	}

	private BonificoDto updateAndReturnSame(BonificoDto item) {
				
		if(!item.isProcessed()){
			log.info("Bonifico# "+item.getOrderId()+ " Processed");
			 BonificoDtoUtils.updateDescription(item);
			 item.setProcessed(true);		
		}
		
		 return item;		 	
	}
	
	

}

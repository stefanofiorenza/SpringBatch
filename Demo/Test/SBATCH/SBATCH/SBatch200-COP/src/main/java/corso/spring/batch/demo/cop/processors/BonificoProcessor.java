package corso.spring.batch.demo.cop.processors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.AbstractCommonItemChunkExcManager;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;

@Slf4j
public class BonificoProcessor extends AbstractCommonItemChunkExcManager implements ItemProcessor<BonificoDto,BonificoDto> {
	
		
	@Override
	public BonificoDto process(BonificoDto item) throws Exception {
				
	
		evaluateExceptions(item);
		
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

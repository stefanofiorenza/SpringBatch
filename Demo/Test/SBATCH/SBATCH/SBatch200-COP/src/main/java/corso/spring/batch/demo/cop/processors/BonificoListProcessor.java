package corso.spring.batch.demo.cop.processors;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;

@Slf4j
public class BonificoListProcessor implements ItemProcessor<List<BonificoDto>,List<BonificoDto>>{

	@Override
	public List<BonificoDto> process(List<BonificoDto> items) throws Exception {
		
		log.info("Bonifico To process: "+items.size());
		
		
		
		//1) Fault tolerant copy of immutable input
		return updateAndReturnNew(items);
		
		//2) No fault tolerant
		//return updateAndReturnSame(items);		
		
	}
	
	
	private List<BonificoDto> updateAndReturnNew(List<BonificoDto> items){
		
		List<BonificoDto> newBonifici= new ArrayList<BonificoDto>();
		int readCount=0;
		for (BonificoDto original : items){
			
			//1) Exceptions
			//ExceptionDemoUtils.evaluateExceptions(readCount, this.getClass());			
			
			//2) ordinary handling
			BonificoDto newBonifico=BonificoDtoUtils.updateAndReturnNew(original);
			
			readCount++;
		}		
		return newBonifici;
	}
	
	private List<BonificoDto> updateAndReturnSame(List<BonificoDto> items){
		
		int readCount=0;
		for (BonificoDto bonifico : items){
			
			//1) Exceptions
			ExceptionDemoUtils.evaluateExceptions(readCount, this.getClass());			
			
			//2) ordinary handling			
			BonificoDtoUtils.updateDescription(bonifico); //works on original!!
			readCount++;
		}
		return items;
	}
	
	

}

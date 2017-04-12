package corso.spring.batch.demo.cop.processors;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.processors.beans.BonificoProcessingWrapper;

@Slf4j
public class BonificoProcessorChain1 implements ItemProcessor<BonificoDto,BonificoProcessingWrapper>{

	@Override
	public BonificoProcessingWrapper process(BonificoDto item) throws Exception {		
		BonificoProcessingWrapper bonificoProcWrapper= new BonificoProcessingWrapper();
		bonificoProcWrapper.setBonifico(item);		
		log.info("received: "+item+" will return "+bonificoProcWrapper);
		return bonificoProcWrapper;
	}

}

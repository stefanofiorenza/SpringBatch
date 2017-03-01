package corso.spring.batch.demo.cop.processors;

import lombok.extern.log4j.Log4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.cop.dto.BonificoDto;

@Log4j
public class BonificoProcessor implements ItemProcessor<BonificoDto,BonificoDto>{

	@Override
	public BonificoDto process(BonificoDto item) throws Exception {
		log.info("Bonifico Processed: "+item);
		return item;
	}

}

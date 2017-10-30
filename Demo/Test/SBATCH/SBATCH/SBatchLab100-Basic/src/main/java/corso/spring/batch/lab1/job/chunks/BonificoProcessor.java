package corso.spring.batch.lab1.job.chunks;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.commons.dto.BonificoDto;

public class BonificoProcessor implements ItemProcessor<BonificoDto, BonificoDto>{

	private static final BigDecimal LIMIT= new BigDecimal("300");
	
	@Override
	public BonificoDto process(BonificoDto item) throws Exception {

		if (item.getAmount().compareTo(LIMIT)>0){
			return item;
		}
		
		return null;
	}

}

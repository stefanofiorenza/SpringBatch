package corso.spring.batch.demo.cop.jdbc.wrappers;

import lombok.Getter;
import lombok.Setter;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.AbstractCommonItemChunkExcManager;

public class JdbcItemReaderWrapper extends AbstractCommonItemChunkExcManager implements  ItemReader<BonificoDto>{

	
	@Getter
	@Setter
	private ItemReader<BonificoDto> delegate;
	
	@Override
	public BonificoDto read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
		BonificoDto bonifico= delegate.read();		
		evaluateExceptions(bonifico);
		return bonifico;
	}

}

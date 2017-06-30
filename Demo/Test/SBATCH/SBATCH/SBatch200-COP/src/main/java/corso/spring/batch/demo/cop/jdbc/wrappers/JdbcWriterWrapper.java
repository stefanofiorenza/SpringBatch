package corso.spring.batch.demo.cop.jdbc.wrappers;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.AbstractCommonItemChunkExcManager;

public class JdbcWriterWrapper extends AbstractCommonItemChunkExcManager implements ItemWriter<BonificoDto>{

	@Getter
	@Setter
	private JdbcBatchItemWriter<BonificoDto> delegate;
	
	@Override
	public void write(List<? extends BonificoDto> items) throws Exception {
		evaluateExceptions(items);
		delegate.write(items);		
	}

}

package corso.spring.batch.demo.cop.processors.beans;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import lombok.Data;

@Data
public class BonificoProcessingWrapper {

	private BonificoDto bonifico;
	private AccountingDto accountingData;
	private OperationDto operationData;
	
}

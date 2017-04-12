package corso.spring.batch.demo.cop.processors;

import lombok.extern.log4j.Log4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.processors.beans.AccountingDto;
import corso.spring.batch.demo.cop.processors.beans.BonificoProcessingWrapper;
import corso.spring.batch.demo.cop.processors.beans.OperationDto;

@Log4j
public class BonificoProcessorChain3 implements ItemProcessor<BonificoProcessingWrapper,BonificoProcessingWrapper>{

	@Override
	public BonificoProcessingWrapper process(BonificoProcessingWrapper item) throws Exception {
		OperationDto operationData= new OperationDto();
		operationData.setOperationData("Some mock data added  from Mock DB");
		item.setOperationData(operationData);
		log.info("received: "+item+" will return "+item);
		return item;
	}

}

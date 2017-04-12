package corso.spring.batch.demo.cop.processors;

import lombok.extern.log4j.Log4j;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.processors.beans.AccountingDto;
import corso.spring.batch.demo.cop.processors.beans.BonificoProcessingWrapper;

@Log4j
public class BonificoProcessorChain2 implements ItemProcessor<BonificoProcessingWrapper,BonificoProcessingWrapper>{

	@Override
	public BonificoProcessingWrapper process(BonificoProcessingWrapper item) throws Exception {
		AccountingDto accountingData = new AccountingDto();
		accountingData.setAddedData("Added data from DB from BonificoProcessorChain2");
		item.setAccountingData(accountingData);
		log.info("received: "+item+" will return "+item);
		return item;
	}

}

package corso.spring.batch.demo.cop.flatfiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.transform.FieldExtractor;

import corso.spring.batch.demo.commons.dto.BonificoDto;

public class BonificoFieldExtractor implements FieldExtractor<BonificoDto>{

	@Override
	public Object[] extract(BonificoDto item) {
		
		return new Object[]{
			item.getOrderId(),
			item.getIbanPayer(),
			item.getIbanBeneficiary(),
			item.getPayer(),
			item.getBeneficiary(),
			item.getAmount(),
			item.getDate(),
			item.getDescription(),
			item.getReferenceNumber()				
		};
	}



}

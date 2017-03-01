package corso.spring.batch.demo.cop.flatfiles;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;


import corso.spring.batch.demo.cop.dto.BonificoDto;

public class BonificoFieldSetMapper  implements FieldSetMapper<BonificoDto>{

	@Override
	public BonificoDto mapFieldSet(FieldSet fieldSet) throws BindException {
	
		BonificoDto bonifico= new BonificoDto();
		bonifico.setAmount(fieldSet.readBigDecimal("amount"));
		bonifico.setBeneficiary(fieldSet.readString("beneficiary"));	
		bonifico.setDate(fieldSet.readString("date"));
		bonifico.setDescription(fieldSet.readString("description"));
		bonifico.setIbanBeneficiary(fieldSet.readString("ibanBeneficiary"));
		bonifico.setIbanPayer(fieldSet.readString("ibanPayer"));
		bonifico.setOrderId(fieldSet.readString("orderId"));
		bonifico.setPayer(fieldSet.readString("payer"));
		bonifico.setReferenceNumber(fieldSet.readString("referenceNumber"));
		return bonifico;
	}

}

package corso.spring.batch.demo.cop.common;

import java.math.BigDecimal;

import corso.spring.batch.demo.commons.dto.BonificoDto;

public class BonificoDtoUtils {

	
	public static BonificoDto createMockBonifico(int index){
		BonificoDto bonifico= new BonificoDto();
		bonifico.setAmount(new BigDecimal("105.87"));
		bonifico.setBeneficiary("MockBeneficiary"+index);	
		bonifico.setDate("2014-10-19");
		bonifico.setDescription("Bolletta Gas"+index);
		bonifico.setIbanBeneficiary("IT88O6901885822051792072363");
		bonifico.setIbanPayer("IT03Y1614290751148034607296");
		bonifico.setOrderId("Y161429075114803_"+index);
		bonifico.setPayer("MockPayer"+index);
		bonifico.setReferenceNumber("34502888821944_"+index);
		
		return bonifico;
	}
	
	
	public static void updateDescription(BonificoDto original){		
		original.setDescription(original.getDescription()+"_Processed");		
	}
	
	public static BonificoDto updateAndReturnNew(BonificoDto original){
		BonificoDto newBonifico=BonificoDtoUtils.copy(original);	
		updateDescription(newBonifico);
		return newBonifico;
	}
	
	public static BonificoDto copy(BonificoDto original){
		BonificoDto bonifico= new BonificoDto();
		bonifico.setAmount(original.getAmount());
		bonifico.setBeneficiary(original.getBeneficiary());	
		bonifico.setDate(original.getDate());
		bonifico.setDescription(original.getDescription());
		bonifico.setIbanBeneficiary(original.getIbanBeneficiary());
		bonifico.setIbanPayer(original.getIbanPayer());
		bonifico.setOrderId(original.getOrderId());
		bonifico.setPayer(original.getPayer());
		bonifico.setReferenceNumber(original.getReferenceNumber());		
		return bonifico;
	}
}

package corso.spring.batch.demo.commons.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class BonificoDto {

	private String orderId;
	private String ibanPayer;
	private String ibanBeneficiary;
	private String payer;
	private String beneficiary;	
	private BigDecimal amount;
	private String date;
	private String description;
	private String referenceNumber;
	
	private boolean processed=false;
	
	public String getIbanState(){
		return ibanPayer.substring(0,2);
	}
	
}

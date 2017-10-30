package corso.spring.batch.lab1.utils;

import java.math.BigDecimal;

public class Consts {
	
	public static final String REPORT_FULL_PATH = "C:/Temp/Report";
	public static final String IBAN_INPUT_CSV_PATH = "C:/Temp/Sbatch/Lab01/Ibans.csv";
	public static final String PAYMENT_INPUT_CSV_PATH = "C:/Temp/Sbatch/Lab01/Payments.csv";
	
	
	
	public static final String DELIMITER = ";";
	
	public static final String NEW_LINE_SEPARATOR = "\n";
	
	public static final String [] FILE_HEADER = {
		"orderId",
		"ibanPayer",
		"ibanBeneficiary",
		"payer",
		"beneficiary",	
		"amount",
		"date",
		"description",
		"referenceNumber"
	};


}

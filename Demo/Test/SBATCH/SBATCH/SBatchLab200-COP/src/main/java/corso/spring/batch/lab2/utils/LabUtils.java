package corso.spring.batch.lab2.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LabUtils {

	
	public static boolean dirExists(String fullPath){
		return Files.exists(Paths.get(fullPath), LinkOption.NOFOLLOW_LINKS);		
	}
	
	public static boolean createDirectory(String fullPath){
		
		boolean success = new File(fullPath).mkdir();

		if (success) {
			log.info("Created: " + fullPath);
			
		} else {
			if(dirExists(fullPath)){
				log.warn(fullPath+" is already existing");
			}else{
				log.error("Impossibile creare: " + fullPath);
			}			
		}
		
		return success;
	}
	
	
	public static BonificoDto parseBonifico(CSVRecord csvRecord){		
		
		//orderId,payer,ibanPayer,beneficiary,ibanBeneficiary,amount,date,description,referenceNumber
		
		BonificoDto bonifico = new BonificoDto();
		bonifico.setOrderId(csvRecord.get(Consts.FILE_HEADER[0]));
		bonifico.setPayer(csvRecord.get(Consts.FILE_HEADER[1]));
		bonifico.setIbanPayer(csvRecord.get(Consts.FILE_HEADER[2]));
		bonifico.setBeneficiary(csvRecord.get(Consts.FILE_HEADER[3]));
		bonifico.setIbanBeneficiary(csvRecord.get(Consts.FILE_HEADER[4]));		
		String amountAsString= csvRecord.get(Consts.FILE_HEADER[5]);
		bonifico.setAmount(new BigDecimal(amountAsString));
		bonifico.setDate(csvRecord.get(Consts.FILE_HEADER[6]));
		bonifico.setDescription(csvRecord.get(Consts.FILE_HEADER[7]));
		bonifico.setReferenceNumber(csvRecord.get(Consts.FILE_HEADER[8]));
		return bonifico;
	}
	
	
	
	public static void writeBonifico (String path, BonificoDto bonifico){
		
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
	    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(Consts.NEW_LINE_SEPARATOR);
				
		try {
			fileWriter = new FileWriter(path);
		    csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
	        csvFilePrinter.printRecord(bonifico);
	        
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			
		} finally {
			
			try {
				fileWriter.flush();							
			} catch (IOException e) {
				log.error(e.getMessage(),e);
            }
			
			IOUtils.closeQuietly(fileWriter);
			IOUtils.closeQuietly(csvFilePrinter);
		}
		
	}
	
	

}

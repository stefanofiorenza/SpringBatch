package corso.spring.batch.lab1.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.lab1.utils.Consts;
import corso.spring.batch.lab1.utils.LabUtils;

public class TestPaymentReader {

	public static void main(String[] args) throws IOException {

		Reader in = new FileReader(Consts.PAYMENT_INPUT_CSV_PATH);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(Consts.FILE_HEADER).withSkipHeaderRecord().parse(in);
		
		
		for (CSVRecord record : records){
			BonificoDto bonifico = LabUtils.parseBonifico(record);
			LabUtils.writeBonifico(Consts.REPORT_FULL_PATH+"/"+bonifico.getIbanState(), bonifico);
		}
		
//		LabUtils.writeBonifico("C:/Temp/bonifico.csv",bonifico);
		

	}

}

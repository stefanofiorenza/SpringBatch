
package corso.spring.batch.lab1.job.chunks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.lab1.utils.Consts;
import corso.spring.batch.lab1.utils.LabUtils;

@Slf4j
public class BonificoCsvReader implements ItemReader<BonificoDto> {

	private Iterable<CSVRecord> records;
	
	
	@BeforeStep
    public void initReader(StepExecution stepExecution) {
       
		Reader in;
		try {
			in = new FileReader(Consts.PAYMENT_INPUT_CSV_PATH);
			log.info("Parsing from : "+Consts.PAYMENT_INPUT_CSV_PATH);
			records = CSVFormat.DEFAULT.withHeader(Consts.FILE_HEADER).parse(in);
			
			log.info("Parsed from : "+Consts.PAYMENT_INPUT_CSV_PATH);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
    }
	
	
	@Override
	public BonificoDto read() throws Exception, UnexpectedInputException,ParseException, NonTransientResourceException {
		
		BonificoDto bonificoParsed= null;
		try{
			if(records.iterator().hasNext()){
				 bonificoParsed=LabUtils.parseBonifico(records.iterator().next());
				 log.info("Parsed: "+bonificoParsed.toString());
			}
		}catch (NoSuchElementException e){
			return null;
		}
				
		return  bonificoParsed;
		
	}

}

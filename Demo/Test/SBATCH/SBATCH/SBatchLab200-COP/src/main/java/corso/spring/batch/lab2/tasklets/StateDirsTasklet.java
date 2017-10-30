package corso.spring.batch.lab2.tasklets;

import java.io.FileReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.lab2.utils.Consts;
import corso.spring.batch.lab2.utils.LabUtils;



public class StateDirsTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)throws Exception {

		Reader in = new FileReader(Consts.IBAN_INPUT_CSV_PATH);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
		
		for(CSVRecord record : records) {
		   String ibanToParse = record.get("ibanPayer");
		   String ibanState=ibanToParse.substring(0,2);
		   LabUtils.createDirectory(Consts.REPORT_FULL_PATH+"/"+ibanState);
		}		
		return RepeatStatus.FINISHED;
		
	}
	
	
//	private void saveInJobExecutionContext(StepExecution stepExecution, String pathToSave){
//		
//		JobExecution jobExecution = stepExecution.getJobExecution();
//		ExecutionContext jobExecutionCtx=jobExecution.getExecutionContext();
//		jobExecutionCtx.put(Consts.VALUE_KEY, pathToSave);	
//	}

}

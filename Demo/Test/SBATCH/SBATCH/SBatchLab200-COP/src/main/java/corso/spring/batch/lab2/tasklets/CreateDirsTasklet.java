package corso.spring.batch.lab2.tasklets;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.lab2.utils.Consts;
import corso.spring.batch.lab2.utils.LabUtils;


@Slf4j
public class CreateDirsTasklet implements Tasklet{

	
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) {

		
		log.debug("Start CreateDirsTasklet ");
		LabUtils.createDirectory(Consts.REPORT_FULL_PATH);
		return RepeatStatus.FINISHED;	
	}


}

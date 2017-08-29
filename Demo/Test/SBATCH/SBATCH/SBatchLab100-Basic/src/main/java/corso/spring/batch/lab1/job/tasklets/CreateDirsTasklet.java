package corso.spring.batch.lab1.job.tasklets;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.lab1.launcher.Config;

@Slf4j
public class CreateDirsTasklet implements Tasklet{

	
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) {
//		try{
		
			log.debug("Start CreateDirsTasklet ");
			
		    String reportDirectory = Config.PATH + "report";
		    boolean success = (new File(reportDirectory)).mkdir();

		    if (success)
		    {
		      System.out.println("Ho creato: " + reportDirectory);
		      
		      return RepeatStatus.FINISHED;
		    }else{
		      System.out.println("Impossibile creare: " + reportDirectory);
		      return null;
		    }
//		}catch (IOException e){
//			
//		}
	
	}

}

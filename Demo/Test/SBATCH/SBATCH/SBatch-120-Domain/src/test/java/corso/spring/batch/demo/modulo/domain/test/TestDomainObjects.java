package corso.spring.batch.demo.modulo.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.batch.demo.commons.test.AbstractDemoTest;




@Slf4j
public class TestDomainObjects extends AbstractDemoTest{

		
	//@Test
	public void manualJobRestart() throws Exception{
		loadContext(Configs.COP_TC01);		
		
		//1) start by jobName
		//Long jobExecutionId=jobOperator.start(Configs.CHUNCK_JOB_NAME_BASIC, EMPTY_PARAMS);	
		
		//2) Restart by JobExecutionId
		//jobOperator.restart(197L);
		
	}

	@Test
	public void testCOPOrdinaryExecution() throws Exception{								
		testTemplate(Configs.COP_TC01, Configs.COP_TC01_JOB_NAME, 1,1,BatchStatus.COMPLETED);		
	}
	
	
	//@Test //enable exception in Job
	public void testCOPExceptionExecution() throws Exception{								
	
		//1) Exception in Reader, Processor, Writer
		int noStepsInJob=1;
		int expectedJobExecution=getRestartLimit()+1;
		int expectedStepExecution=expectedJobExecution*noStepsInJob;
		
		testTemplate(Configs.COP_TC02, Configs.COP_TC02_JOB_NAME, expectedJobExecution,expectedStepExecution,BatchStatus.FAILED);
	}
	    
	@Test
	public void testTaskletOrdinaryExecution() throws Exception{								
		testTemplate(Configs.TASKLET_TC01, Configs.TASKLET_TC01_JOB_NAME, 1,3,BatchStatus.COMPLETED);
	}
	
	
	
	
}

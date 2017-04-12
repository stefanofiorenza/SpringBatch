package corso.spring.batch.demo.commons.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
public class AbstractDemoTest {

	// loaded in loadContext() 
		protected AbstractApplicationContext context;
		protected JobOperator jobOperator;	
		protected JobExplorer explorer;
		
		// init/updated in updateDomainObjectsByJobExecutionId() 
		protected JobExecution jobExecution;
		protected JobInstance jobInstance;
		protected List<JobExecution> executionsByJobInstance;
		protected Collection<StepExecution> stepExecutions;
		
		
		// Consts
		//protected final String EMPTY_PARAMS="";
		
		//Settings
		@Getter
		@Setter
		private int restartLimit=3;
		
		
		protected void testTemplate(String configFile, String jobName, 
				int expectedJobExecutions, int expectedStepExecutions, BatchStatus expectedBatchStatus) throws Exception{
			
			loadContext(configFile);		
			
			String params="dataStart="+new Date();
			Long jobExecutionId=jobOperator.start(jobName, params);		
		
			updateDomainObjectsByJobExecutionId(jobExecutionId);
			
			waitUntilJobExecutionIsOver();
			
			restartIfNotCompleted(jobExecutionId,restartLimit);		
					
			domainObjAssertions(expectedJobExecutions ,expectedStepExecutions,expectedBatchStatus);	
			
			context.close();
		}
		
		private void waitUntilJobExecutionIsOver() throws Exception{
			while(jobExecution.isRunning()){	
				//idle loop to wait for the end of job execution 
			}		
		}
		
		
		protected void restartIfNotCompleted(final Long jobExecutionId, final int restartLimit ) 
				throws JobInstanceAlreadyCompleteException, NoSuchJobExecutionException, NoSuchJobException, JobRestartException, JobParametersInvalidException{
			
			int restarted=0;		
			while(jobExecution.getStatus()!=BatchStatus.COMPLETED && restarted<restartLimit){
				Long restartedExecutionId=jobOperator.restart(jobExecutionId);
				updateDomainObjectsByJobExecutionId(restartedExecutionId);			
				restarted++;
			}
		}
		
		
		protected void loadContext(String config) {
		    	context = new ClassPathXmlApplicationContext(config);
		    	explorer =  context.getBean(JobExplorer.class);
		    	jobOperator=context.getBean(JobOperator.class);	
		 }
		 
		 
		protected void updateDomainObjectsByJobExecutionId(Long jobExId) {
			 	log.info("Load Sbatch domain objects for JobExecutionId: "+jobExId);
			 	
			 	//domain objects
				jobExecution=explorer.getJobExecution(jobExId);
				jobInstance= explorer.getJobInstance(jobExecution.getJobInstance().getId());
				executionsByJobInstance=explorer.getJobExecutions(jobInstance);
				stepExecutions=jobExecution.getStepExecutions();
				
				assertNotNull(jobInstance);
				assertNotNull(executionsByJobInstance);
				assertNotNull(stepExecutions);
		 }
		 
		private void domainObjAssertions(
				int expectedJobExecution,
				int expectedStepExecution,
				BatchStatus expectedStatus) throws Exception{
			
			int actualStepExecutions=0;
			
			for (JobExecution jEx : executionsByJobInstance){
				actualStepExecutions+=jEx.getStepExecutions().size();				
			}
					
			assertEquals(expectedStatus, jobExecution.getStatus());		
			assertEquals("JobExecution is not matching expectations",expectedJobExecution, executionsByJobInstance.size());
			assertEquals("StepExecution is not matching expectations" ,expectedStepExecution, actualStepExecutions);
			
				
		}
	
}

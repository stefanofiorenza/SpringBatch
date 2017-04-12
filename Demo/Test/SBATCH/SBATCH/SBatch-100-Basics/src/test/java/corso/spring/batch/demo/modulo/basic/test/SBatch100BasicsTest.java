package corso.spring.batch.demo.modulo.basic.test;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.batch.demo.commons.utils.SbatchContextUtils;
import corso.spring.batch.demo.commons.utils.SbatchJobUtils;
import corso.spring.batch.demo.modulo.basic.launcher.Configs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SBatch100BasicsTest {

	AbstractApplicationContext context;
	
	
	
	@Test 
	public void demoBasicTasklet() throws Exception{
		testTemplate(Configs.TASKLET_CFG_01_BASIC, Configs.TASKET_JOB_NAME_BASIC);		
	}
	
	@Test
	public void demoBasicChunkOrientedProcessing() throws Exception{
		testTemplate(Configs.COP_CFG_01_BASIC, Configs.CHUNCK_JOB_NAME_BASIC);
	}
	
	@Test
	public void demoCOPWithListeners() throws Exception{
		testTemplate(Configs.COP_CFG_02_LISTENERS,Configs.CHUNCK_JOB_NAME_LISTENERS);
	}
	
	private void testTemplate(String configFile, String jobName) throws Exception{
		context = new ClassPathXmlApplicationContext(configFile);
		
		SbatchJobUtils.startJob(context, jobName);
		
		JobExecution jobExecution=SbatchContextUtils.getLastJobExecutionByJobName(context, Configs.TASKET_JOB_NAME_BASIC);
		while(jobExecution.isRunning()){
			//wait until jobExecution is terminated
		}		
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		context.close();	
	}
}

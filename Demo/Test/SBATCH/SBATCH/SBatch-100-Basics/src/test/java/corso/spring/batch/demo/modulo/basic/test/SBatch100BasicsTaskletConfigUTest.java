package corso.spring.batch.demo.modulo.basic.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import corso.spring.batch.demo.commons.config.CommonSbatchConfiguration;
import corso.spring.batch.demo.commons.utils.SbatchContextUtils;
import corso.spring.batch.demo.commons.utils.SbatchJobUtils;
import corso.spring.batch.demo.modulo.basic.config.ChunkProcessingConfig;
import corso.spring.batch.demo.modulo.basic.config.TaskletConfig;
import corso.spring.batch.demo.modulo.basic.launcher.Configs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TaskletConfig.class)
public class SBatch100BasicsTaskletConfigUTest {

	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
	
	
	
	@Test 
	public void demoBasicTasklet() throws Exception{
		JobExecution jobExecution=jobLauncherTestUtils.launchJob(); //unico job per unico ApplicationContext di test
		
		while(jobExecution.isRunning()){
			//wait until jobExecution is terminated
		}		
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}
	

}

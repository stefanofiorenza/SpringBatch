package corso.spring.batch.demo.modulo.springbatch2.basic.launcher;

import org.junit.Test;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class TestTaskletDemoLauncher extends TestCase {

	
	//TODO
	// Da sistemare il test case ed aggiungere anche il chunck processing
	
//	@Test
//	public void testDemoTasklet(){
//		
//		ApplicationContext contesto =new ClassPathXmlApplicationContext(
//        "corso/spring/batch/demo/modulo/springbatch2/basic/config/Basic-Tasklet-SpringBatch2.xml");
//		JobLauncher launcher= (JobLauncher) contesto.getBean("myJobLauncher");
//		CommandLineJobRunner runner = new CommandLineJobRunner();
//		runner.setLauncher(launcher);
//		runner.main(new String[]{});
//		
//	}
}

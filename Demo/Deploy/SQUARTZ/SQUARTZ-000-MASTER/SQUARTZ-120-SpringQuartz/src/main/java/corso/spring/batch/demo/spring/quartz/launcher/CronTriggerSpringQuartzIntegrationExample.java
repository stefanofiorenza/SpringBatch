package corso.spring.batch.demo.spring.quartz.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CronTriggerSpringQuartzIntegrationExample {

	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		"corso/spring/batch/demo/spring/quartz/job/config/CronTrigger-context.xml");
		}
	
}

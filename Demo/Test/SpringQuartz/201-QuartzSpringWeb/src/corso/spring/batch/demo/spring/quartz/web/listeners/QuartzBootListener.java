package corso.spring.batch.demo.spring.quartz.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzBootListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Fine Applicazione");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		"corso/spring/batch/demo/spring/quartz/config/helloJob-CronTrigger-context.xml");
		
	}

}

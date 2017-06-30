package corso.spring.batch.demo.modulo.basic.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.xml.SimpleFlowFactoryBean;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import corso.spring.batch.demo.commons.config.CommonSbatchConfiguration;
import corso.spring.batch.demo.modulo.basic.job.tasklets.HelloWorldTasklet;
import corso.spring.batch.demo.modulo.basic.job.tasklets.SecondaTasklet;
import corso.spring.batch.demo.modulo.basic.job.tasklets.UltimaTasklet;

@Configuration
@Import(CommonSbatch100Config.class)
public class TaskletConfig {

	@Autowired
	private CommonSbatch100Config commonSbatch100Config;
	

	
	
	@Bean
	public Step step1(){
		return commonSbatch100Config.stepBuilders
				.get("SB100-TaskletListeners-S1")
				.tasklet(new HelloWorldTasklet())
				.build();
	}
	
	@Bean
	public Step step2(){
		return commonSbatch100Config.stepBuilders
				.get("SB100-TaskletListeners-S2")
				.tasklet(new SecondaTasklet())
				.build();
	}
	
	@Bean
	public Step step3(){
		return commonSbatch100Config.stepBuilders
				.get("SB100-TaskletListeners-S3")
				.tasklet(new UltimaTasklet())
				.build();
	}

	
	@Bean
	public Job job(){
		return commonSbatch100Config.jobBuilders
				.get("SB100-TaskletListeners-Job")
				.start(step1()).next(step2()).next(step3())
				.build();
	}
	
	@Bean
	public JobLauncherTestUtils launcherTestUtils(){
		return new JobLauncherTestUtils();
	}
}



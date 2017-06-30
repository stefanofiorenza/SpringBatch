package corso.spring.batch.demo.modulo.basic.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import corso.spring.batch.demo.common.exceptions.SkipThresholdException;
import corso.spring.batch.demo.commons.config.CommonSbatchConfiguration;
import corso.spring.batch.demo.commons.config.SbatchDaoConfig;
import corso.spring.batch.demo.modulo.basic.job.io.UserProcessor;
import corso.spring.batch.demo.modulo.basic.job.io.UserReader;
import corso.spring.batch.demo.modulo.basic.job.io.UserWriter;
import corso.spring.batch.demo.modulo.basic.job.io.model.User;
import corso.spring.batch.demo.modulo.basic.listeners.StepExecutionListenerLogImpl;

@Configuration
@Import(CommonSbatch100Config.class)
public class ChunkProcessingConfig {

	@Autowired
	private CommonSbatch100Config commonSbatch100Config;

	
	
	@Bean
	public ItemReader<User> userReader(){
		return new UserReader();
	}
	
	@Bean
	public ItemProcessor<User,User> userProcessor(){
		return new UserProcessor();
	}
	
	@Bean
	public ItemWriter<User> userWriter(){
		return new UserWriter();
	}
	
	@Bean
	public StepExecutionListener logStepExecutionListener(){
		return new StepExecutionListenerLogImpl();
	}
	
	@Bean
	public Step step(){
		return commonSbatch100Config.stepBuilders.get("SB100-COPListeners-Job-S1")
				.<User,User>chunk(10) //commit interval
				.faultTolerant() //to enable skip methods
				.skip(SkipThresholdException.class)	
				.skipLimit(10)				
				.reader(userReader())
				.processor(userProcessor())
				.writer(userWriter())				
				.listener(logStepExecutionListener())							
				.build();				
	}
	
	@Bean
	public Job job(){
		return commonSbatch100Config.jobBuilders.get("SB100-COPListeners-Job")
				.start(step())
				.build();
	}
	
	@Bean
	public JobLauncherTestUtils launcherTestUtils(){
		return new JobLauncherTestUtils();
	}
	
}

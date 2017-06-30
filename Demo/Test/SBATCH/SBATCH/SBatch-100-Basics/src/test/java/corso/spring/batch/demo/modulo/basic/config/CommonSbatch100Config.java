package corso.spring.batch.demo.modulo.basic.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import corso.spring.batch.demo.commons.config.CommonSbatchConfiguration;

@Configuration
@Import(CommonSbatchConfiguration.class)
public class CommonSbatch100Config {

	
	@Autowired
	JobBuilderFactory jobBuilders;
 
	@Autowired
	StepBuilderFactory stepBuilders;
	
}

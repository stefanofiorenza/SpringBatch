package corso.spring.batch.demo.commons.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableBatchProcessing
@Import(SbatchDaoConfig.class)
public class CommonSbatchConfiguration {

	/**
	@EnableBatchProcessing
	Creates and bind 
	-  JobRepository working with the transaction manager and the DataSource, 
	-  JobLauncher using the JobRepository. 	
	-  JobRegistry to find jobs by name
	-  JobBuilderFactory
	-  StepBuilderFactory 
	-  In addition it registers the StepScope for usage on batch components	
	*/
	
	@Autowired
	private SbatchDaoConfig sbatchDao;
	
	
	// oggetti creati da @EnableBatchProcessing disponibili in autowired
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobRegistry jobRegistry;
	
	/* Used in Job/Step Configuration
	 * 
	@Autowired
	private JobBuilderFactory jobBuilders;
 
	@Autowired
	private StepBuilderFactory stepBuilders;
	
	*/
	
	
	@Bean
	public JobExplorer jobExplorer() throws Exception{
		JobExplorerFactoryBean jobExplorer= new JobExplorerFactoryBean();
		//jobExplorer.setDataSource(sbatchDao.sbatchDatasource());
		jobExplorer.setJdbcOperations(sbatchDao.jdbcTemplate());
		return jobExplorer.getObject();
	}
		
	@Bean
	public JobOperator  jobOperator() throws Exception{
		SimpleJobOperator jobOperator = new SimpleJobOperator();
		jobOperator.setJobExplorer(jobExplorer());
		jobOperator.setJobRegistry(jobRegistry);
		jobOperator.setJobLauncher(jobLauncher);
		jobOperator.setJobRepository(jobRepository);
		return jobOperator;
	}
	
	
}




package corso.spring.batch.demo.commons.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SbatchDaoConfig {

	
	@Bean
	public DataSource sbatchDatasource(){		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/sbatch3");
		dataSource.setUsername("root");
		dataSource.setPassword("stefan0");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager sbatchTransactionManager(){
		return new DataSourceTransactionManager(sbatchDatasource());
	}
	
	
	@Bean
	public JdbcTemplate  jdbcTemplate(){ //needed for JobExplorer
		return new JdbcTemplate(sbatchDatasource());
	}
	
	
}

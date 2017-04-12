package corso.spring.batch.demo.commons.utils;

import java.util.Date;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SbatchJobUtils {

	
	public static void startJobFromCommandLine(String configurationFilePath,String jobName){
		try{
			
			Date adesso = new Date();
			String optParameter="dataEsecuzione="+adesso;
			
			String [] mainArguments={
					configurationFilePath,
					jobName,
					optParameter
			};
			
			// codice cablato di esecuzione da riga di comando..
			CommandLineJobRunner.main(mainArguments);
						
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static Long startJob(ApplicationContext context,String nameJob) throws Exception{
		Date adesso = new Date();
		String defaultParams="dataEsecuzione="+adesso;
		return startJob(context,nameJob,defaultParams);
	}
	
	public static Long startJob(ApplicationContext context,String nameJob, String parameters) throws Exception{
		JobOperator jobOperator=context.getBean(JobOperator.class);		
		return jobOperator.start(nameJob, parameters);
	}
	
	public static Long restartJob(ApplicationContext context,Long jobExecutionId) throws Exception{
		JobOperator jobOperator=context.getBean(JobOperator.class);		
		return jobOperator.restart(jobExecutionId);
	}
	
	public static boolean stopJob(ApplicationContext context,Long jobExecutionId) throws Exception{
		JobOperator jobOperator=context.getBean(JobOperator.class);		
		return jobOperator.stop(jobExecutionId);
	}
	
	
		/*
		 
		 JobExplorer explorer = (JobExplorer) context.getBean(JobExplorer.class);
		JobParameters jobParameters = 
                new JobParametersBuilder()
        			.addString("inputFile",CSV_IN)
        			.addString("outputFile", CSV_OUT).toJobParameters();
		
		
		List<JobInstance> instances =explorer.getJobInstances(nameJob, 0, 1);
		for (JobInstance instance:instances){	
			
			 List<JobExecution> executions = explorer.getJobExecutions(instance);
             System.out.println("Executions size : " + executions.size());
             if(executions.size() > 0) {
                 JobExecution jobExecution = executions.get(executions.size() - 1);
                 if(jobExecution.getStatus().name().equals("FAILED")) {
                     long a= jobOperator.restart(instance.getId());
                     System.out.println("Exit Status : " + a);
                 }
             }
			
		}
		
		*/
	
}

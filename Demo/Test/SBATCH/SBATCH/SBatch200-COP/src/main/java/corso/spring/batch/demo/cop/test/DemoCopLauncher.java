package corso.spring.batch.demo.cop.test;

import java.util.Date;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoCopLauncher {

	
	private static final String CSV_IN="payments-in.csv";
	private static final String CSV_OUT="payments-out.csv";
	private static final String XML_IN="payments-in.xml";
	private static final String XML_OUT="payments-out.xml";
	
	private static final String configurationFilePath="COP-Demo.xml";
	private static final String workingDir="C:/Temp/sbatch/cop/";
			
	private static final Date newEveryTime = new Date();	
	private static final String optParameter="dataEsecuzione="+newEveryTime;	
	private static final String fileIn="inputFile="+workingDir+CSV_IN;
	private static final String fileOut="outputFile="+workingDir+CSV_OUT;
	private static final String restartParam="restarted="+newEveryTime;
	
	public static void main(String[] args) {
	try{
	
			//1) demo no exceptions
			String copDemoSampleJob="copDemoSampleJob";
			
			//2) demo with exceptions
			String copDemoSampleWithExceptionJob="copDemoSampleWithExceptionJob";
			
					
			runJobWithJobOperatorApi(copDemoSampleWithExceptionJob);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	private static void runJobWithParamsFromCommandLine(String nameJob) throws Exception{
		
		String [] mainArgumentsForSampleJob={
				configurationFilePath,
				nameJob,
				optParameter,
				fileIn,
				fileOut
		};
		
		// codice cablato di esecuzione da riga di comando..
		CommandLineJobRunner.main(mainArgumentsForSampleJob);
	}
	
	
	private static void runJobWithJobOperatorApi(String nameJob) throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("COP-Demo.xml");
		JobExplorer explorer = (JobExplorer) context.getBean(JobExplorer.class);
		JobOperator jobOperator=context.getBean(JobOperator.class);
		
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
		
		
		
		
		//jobOperator.
	}
	
	private static void restartJobWithJobOperatorApi(String nameJob) throws Exception{
		
	}
	
	
	private void testMethod(){
		
		 String[] configLocation  = 
	            {    
	                "config/jobs/job-context.xml" 
	            };
	        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
	        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	        Job job = (Job) context.getBean("samplejob1");
	        JobExplorer explorer = (JobExplorer) context.getBean("jobExplorer");
	        JobOperator operator = (JobOperator)context.getBean("jobOperator");
	        JobRegistry registry = (JobRegistry)context.getBean("jobRegistry");
	        try {
	            JobParameters jobParameters = 
	                    new JobParametersBuilder()
	            .addLong("time",System.currentTimeMillis())
	            .addString("fail", "true").toJobParameters();            
//	            JobExecution execution = jobLauncher.run(job, jobParameters);
//	            System.out.println("Exit Status : " + execution.getStatus());


	            List<JobInstance> instances = explorer.getJobInstances("samplejob1", 0, 10);
	            System.out.println("Exploler Size : " + instances.size());
	            for(JobInstance instance:instances) {

	                List<JobExecution> executions = explorer.getJobExecutions(instance);
	                System.out.println("Executions size : " + executions.size());
	                if(executions.size() > 0) {
	                    JobExecution jobExecution = executions.get(executions.size() - 1);
	                    if(jobExecution.getStatus().name().equals("FAILED")) {
	                        long a= operator.restart(instance.getId());
	                        System.out.println("Exit Status : " + a);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}


}

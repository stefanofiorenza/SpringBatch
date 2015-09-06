package corso.spring.batch.demo.springbatch2.web.jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class ScheduledJob extends QuartzJobBean{

	public static final String JOB_NAME="nomeJob";
	
	private Job jobDaEseguire;
	
	public Job getJobDaEseguire() {
		return jobDaEseguire;
	}

	public void setJobDaEseguire(Job jobDaEseguire) {
		this.jobDaEseguire = jobDaEseguire;
	}


	private JobLauncher jobLauncher;
		
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}


	@Override
	protected void executeInternal(JobExecutionContext contesto)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("In Scheduled Job");
		System.out.println("esecuzione job..");
		JobParameter parametro1 = new JobParameter("parametro");
		JobParameter parametro2 = new JobParameter(1L);
		JobParameter parametro3 = new JobParameter(new Date());
		
		Map<String, JobParameter> parametri= new HashMap<String, JobParameter>();
		parametri.put("p1", parametro1);
		parametri.put("p2", parametro2);
		parametri.put("p3", parametro3);
		
		JobParameters parameters=new JobParameters(parametri); //getJobParametersFromJobMap(contesto.getMergedJobDataMap());
		
		try {
			jobLauncher.run(jobDaEseguire, parameters);
			
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {

		JobParametersBuilder builder = new JobParametersBuilder();
		for (Entry<String, Object> entry : jobDataMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			JobParameter parametro = new JobParameter(value);
			
			
		}

		return builder.toJobParameters();

	}
	*/

	
	

}

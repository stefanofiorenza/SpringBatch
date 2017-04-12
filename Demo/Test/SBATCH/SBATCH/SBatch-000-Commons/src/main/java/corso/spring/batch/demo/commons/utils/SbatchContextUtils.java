package corso.spring.batch.demo.commons.utils;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.context.ApplicationContext;

@Slf4j
public class SbatchContextUtils {

	public static JobExecution getLastJobExecutionByJobName(ApplicationContext context, String jobName) throws Exception{
		 JobExplorer explorer = (JobExplorer) context.getBean(JobExplorer.class);		 
		 List<JobInstance> jobInstances=explorer.findJobInstancesByJobName(jobName, 0, 1000);
		 List<JobExecution> executions=explorer.getJobExecutions(jobInstances.get(jobInstances.size()-1));
		 return executions.get(executions.size() - 1);	
	}
}

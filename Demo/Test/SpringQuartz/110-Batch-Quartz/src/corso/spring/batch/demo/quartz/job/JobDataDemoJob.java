package corso.spring.batch.demo.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

public class JobDataDemoJob implements Job {

		
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Map properties = context.getMergedJobDataMap();

        Object contatoreAsObject=properties.get("Contatore");
        // demo stateful
        int contatore;
        if(contatoreAsObject==null){
        	contatore=0;    
        	properties.put("Contatore", contatore);
        }else{
        	contatore=(Integer)contatoreAsObject;
        }         
        contatore++;
        
        System.out.println("Previous Fire Time: "
                + context.getPreviousFireTime());
        System.out.println("Current Fire Time: " + context.getFireTime());
        System.out.println("Next Fire Time: " + context.getNextFireTime());
        System.out.println(properties.get("message"));
        System.out.println(properties.get("jobDetailMessage"));
        System.out.println(properties.get("triggerData"));
        System.out.println(properties.get("chiaveComune"));
        System.out.println(properties.get("Contatore"));
        System.out.println("");
    }
}

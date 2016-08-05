package corso.spring.batch.demo.quartz.launcher;

import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.StdScheduler;

import corso.spring.batch.demo.quartz.job.SimpleTriggerDemoJob;

public class Demo01SimpleTriggerScheduling {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();

        JobDetail jobDetail = new JobDetail("helloWorldJob",
                Scheduler.DEFAULT_GROUP, SimpleTriggerDemoJob.class);

        int ripetizioni=10;
        long intervallo =1000; //ogni secondo;
        Trigger trigger = new SimpleTrigger("helloWorldJob",ripetizioni,intervallo);
        trigger.setGroup(Scheduler.DEFAULT_GROUP);
        trigger.setStartTime(new Date());
        
        scheduler.scheduleJob(jobDetail, trigger);
       
        		
        		
    }
}

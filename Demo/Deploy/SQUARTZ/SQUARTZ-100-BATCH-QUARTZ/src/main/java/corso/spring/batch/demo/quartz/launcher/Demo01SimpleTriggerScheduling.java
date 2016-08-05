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

        //Quartz 1.8
//        JobDetail jobDetail = new JobDetail("helloWorldJob",
//                Scheduler.DEFAULT_GROUP, SimpleTriggerDemoJob.class);
        
       JobDetail jobDetail = JobBuilder.newJob(SimpleTriggerDemoJob.class).withIdentity("helloWorldJob", Scheduler.DEFAULT_GROUP).build();

        int ripetizioni=10;
        int intervallo =1; //ogni secondo;
        
        //Quartz 1.8
//        Trigger trigger = new SimpleTrigger("helloWorldJob",ripetizioni,intervallo);
        
        Trigger trigger =TriggerBuilder.newTrigger().withIdentity("helloWorldJobTrigger", Scheduler.DEFAULT_GROUP).startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervallo).withRepeatCount(ripetizioni)).build();
//        trigger.setGroup(Scheduler.DEFAULT_GROUP);
//        trigger.setStartTime(new Date());
        
        scheduler.scheduleJob(jobDetail, trigger);
       
        		
        		
    }
}

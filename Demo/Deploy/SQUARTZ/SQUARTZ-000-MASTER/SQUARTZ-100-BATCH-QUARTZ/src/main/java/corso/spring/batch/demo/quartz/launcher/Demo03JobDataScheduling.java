package corso.spring.batch.demo.quartz.launcher;

import java.util.Date;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import corso.spring.batch.demo.quartz.job.JobDataDemoJob;

public class Demo03JobDataScheduling {

    public static void main(String[] args) throws Exception {
      
        JobDetail jobDetail = JobBuilder.newJob(JobDataDemoJob.class).withIdentity("messageJob",
                Scheduler.DEFAULT_GROUP).build();

        Map map = jobDetail.getJobDataMap();
        map.put("message", "This is a message from Quartz");      
        map.put("jobDetailMessage", "Dati inseriti da JobDetail");
        map.put("chiaveComune", "NON DOVRESTI VEDERE QUESTO MESSAGGIO!!");
        
        
        Trigger trigger =TriggerBuilder.newTrigger().withIdentity("simpleTrigger", Scheduler.DEFAULT_GROUP).startAt(new Date())
        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)).build();
        
//        Trigger trigger = new SimpleTrigger("simpleTrigger",
//                Scheduler.DEFAULT_GROUP, new Date(), null,
//                SimpleTrigger.REPEAT_INDEFINITELY, 3000);

        Map mappaTrigger= trigger.getJobDataMap();
        mappaTrigger.put("triggerData", "Dati inseriti da Trigger");
        mappaTrigger.put("chiaveComune", "Chiave sovrascritta da Trigger");
        
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        
        scheduler.start();
    }

}

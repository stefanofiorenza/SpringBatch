package corso.spring.batch.demo.quartz.launcher;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import corso.spring.batch.demo.quartz.job.CronTriggerDemoJob;

public class Demo02CronTriggerScheduling {
    
	public static void main(String[] args) throws Exception {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
     
        JobDetail jobDetail = JobBuilder.newJob(CronTriggerDemoJob.class).withIdentity("messageJob", Scheduler.DEFAULT_GROUP).build();

        
        //QUartz 1.8
//        JobDetail jobDetail = new JobDetail("messageJob",
//                Scheduler.DEFAULT_GROUP, CronTriggerDemoJob.class);

        Map map = jobDetail.getJobDataMap();
        map.put("message", "Messaggio Esempio CronTrigger");

        //Significato CronExpression: (Dispensa approfondimento sotto /docs)
        // il terzo secondo di ogni minuto (parte) / ogni 5 secondi
        // delle ore che vanno dalle 11 alle 17
        // di ogni giornoDelMese di ogniMese di qualsiasi (?) giornoSettimana
        //N.B. ? alternativo a * per giornoMese e giornoSettimana
        String cronExpression = "3/5 * 11,12,13,14,15,16,17 * * ?";

      //  String cronExpression = "25/5 * 17 * * ?";


        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger", Scheduler.DEFAULT_GROUP).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }

}

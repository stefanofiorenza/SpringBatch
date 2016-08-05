package corso.spring.batch.demo.quartz.launcher;

import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.CronTrigger;

import corso.spring.batch.demo.quartz.job.CronTriggerDemoJob;

import java.util.Map;
import java.util.Calendar;

public class Demo02CronTriggerScheduling {
    
	public static void main(String[] args) throws Exception {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
     
        JobDetail jobDetail = new JobDetail("messageJob",
                Scheduler.DEFAULT_GROUP, CronTriggerDemoJob.class);

        Map map = jobDetail.getJobDataMap();
        map.put("message", "Messaggio Esempio CronTrigger");

        //Significato CronExpression: (Dispensa approfondimento sotto /docs)
        // il terzo secondo di ogni minuto (parte) / ogni 5 secondi
        // delle ore che vanno dalle 11 alle 17
        // di ogni giornoDelMese di ogniMese di qualsiasi (?) giornoSettimana
        //N.B. ? alternativo a * per giornoMese e giornoSettimana
        String cronExpression = "3/5 * 11,12,13,14,15,16,17 * * ?";

      //  String cronExpression = "25/5 * 17 * * ?";

        Trigger trigger = new CronTrigger("cronTrigger",
                Scheduler.DEFAULT_GROUP, cronExpression);

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }

}

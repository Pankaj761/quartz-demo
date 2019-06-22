package com.example.quartzdemo.service;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleJob implements Job {

	static JobDetail job = JobBuilder.newJob(SampleJob.class)
			.withIdentity("myJob", "group1")
			.build();

	static Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("myTrigger", "group1")
			.startNow()
			.withSchedule(SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInSeconds(5)
					.repeatForever())
			.build();


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		String g = jobExecutionContext.getJobDetail().getDescription();
		System.out.println("hello world !!");
	}

	public static void main(String[] args) {
		try {
			Scheduler s= new StdSchedulerFactory().getScheduler();
			s.start();
			s.scheduleJob(job,trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

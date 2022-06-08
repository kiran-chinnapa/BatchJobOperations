package com.swt.helloworld.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HwJobExecutionListener implements JobExecutionListener {

    @Autowired
    SimpleJobOperator jobOperator;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before starting the Job - Job Name:" + jobExecution.getJobInstance().getJobName());
        System.out.println("before starting the Job" + jobExecution.getExecutionContext().toString());

        jobExecution.getExecutionContext().put("my name", "michael");
        System.out.println("before starting the Job - after set" + jobExecution.getExecutionContext().toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after starting the Job - Job Execution Context" + jobExecution.getExecutionContext().toString());
        try {
            System.out.println("job names:"+jobOperator.getJobNames());
            jobOperator.stop(jobExecution.getJobId());

        } catch (NoSuchJobExecutionException e) {
            e.printStackTrace();
        } catch (JobExecutionNotRunningException e) {
            e.printStackTrace();
        }

    }
}

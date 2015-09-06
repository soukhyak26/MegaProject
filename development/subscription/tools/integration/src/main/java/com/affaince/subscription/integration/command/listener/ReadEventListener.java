package com.affaince.subscription.integration.command.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.*;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by mandark on 19-07-2015.
 */
public class ReadEventListener implements JobExecutionListener {
    private static final Log LOGGER = LogFactory.getLog(ReadEventListener.class);

    public void afterJob(JobExecution jobExecution) {
        StringBuilder readEvent = new StringBuilder();
        readEvent.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        readEvent.append("   JOB NAME " + jobExecution.getJobInstance().getJobName() + " \n");
        readEvent.append("   START_TIME     : " + jobExecution.getStartTime() + "\n");
        readEvent.append("   END_TIME    : " + jobExecution.getEndTime() + "\n");
        readEvent.append("   EXIT_CODE   : " + jobExecution.getExitStatus().getExitCode() + "\n");
        readEvent.append("   EXIT_DETAILS : " + jobExecution.getExitStatus().getExitDescription() + "\n");
        readEvent.append("   STATUS      : " + jobExecution.getStatus() + "\n");
        readEvent.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        readEvent.append("Job-Parameter: \n");
        JobParameters jp = jobExecution.getJobParameters();
        for (Iterator<Map.Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<String, JobParameter> entry = iter.next();
            readEvent.append("  " + entry.getKey() + "=" + entry.getValue() + "\n");
        }
        readEvent.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            readEvent.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            readEvent.append("STEP " + stepExecution.getStepName() + " \n");
            readEvent.append("WRITE_COUNT: " + stepExecution.getWriteCount() + "\n");
            readEvent.append("COMMITS: " + stepExecution.getCommitCount() + "\n");
            readEvent.append("SKIP_COUNT: " + stepExecution.getSkipCount() + "\n");
            readEvent.append("ROLLBACKS: " + stepExecution.getRollbackCount() + "\n");
            readEvent.append("FILTER: " + stepExecution.getFilterCount() + "\n");
            readEvent.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        }
        LOGGER.info(readEvent.toString());
    }

    public void beforeJob(JobExecution arg0) {
        // nothing to do
    }

}

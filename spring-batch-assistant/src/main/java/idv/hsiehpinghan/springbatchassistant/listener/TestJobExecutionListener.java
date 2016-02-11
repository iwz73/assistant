package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class TestJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.err.println("TestJobExecutionListener beforeJob");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.err.println("TestJobExecutionListener afterJob completed.");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			System.err.println("TestJobExecutionListener afterJob failed !!!");
		}
	}

}

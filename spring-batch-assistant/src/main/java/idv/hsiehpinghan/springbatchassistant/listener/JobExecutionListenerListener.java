package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobExecutionListenerListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.err.println("JobExecutionListenerListener beforeJob !!!");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.err.println("JobExecutionListenerListener afterJob !!!");
	}

}

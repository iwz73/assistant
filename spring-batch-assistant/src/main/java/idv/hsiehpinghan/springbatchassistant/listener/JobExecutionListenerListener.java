package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionListenerListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		jobExecution.getExecutionContext().putString("beforeJob", "beforeJob");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		jobExecution.getExecutionContext().putString("afterJob", "afterJob");
	}

}

package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepExecutionListenerListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.err.println("StepExecutionListenerListener beforeStep !!!");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.err.println("StepExecutionListenerListener afterStep !!!");
		return stepExecution.getExitStatus();
	}

}

package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class NextOnToStepExecutionListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {

		System.err.println("before !!!");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.err.println("NextOnToStepExecutionListener afterStep change exist code to NEXT_ON_EXIT_CODE !!!");
		return new ExitStatus("NEXT_ON_EXIT_CODE");
	}

}

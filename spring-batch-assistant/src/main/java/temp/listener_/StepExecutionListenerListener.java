package temp.listener_;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepExecutionListenerListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		stepExecution.getExecutionContext().putString("beforeStep",
				"beforeStep");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getExecutionContext().put("afterStep", "afterStep");
		return ExitStatus.UNKNOWN;
	}

}

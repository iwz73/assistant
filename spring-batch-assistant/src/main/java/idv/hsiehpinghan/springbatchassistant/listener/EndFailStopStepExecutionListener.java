package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class EndFailStopStepExecutionListener implements StepExecutionListener {
	private String batchStatus;
	private boolean isRestart = false;

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (batchStatus.equals("BATCH_STATUS_STOPPED")) {
			if (isRestart == true) {
				return new ExitStatus("BATCH_STATUS_COMPLETED");
			} else {
				isRestart = true;
			}
		}
		return new ExitStatus(batchStatus);
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}

}

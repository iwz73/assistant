package temp.listener_;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class ConditionalFlowListener_0 extends StepExecutionListenerSupport {

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		String exitStatus = "GOTO_1";
		System.err.println("ConditionalFlowListener_0 ExitStatus(" + exitStatus
				+ ")");
		return new ExitStatus(exitStatus);
	}

}

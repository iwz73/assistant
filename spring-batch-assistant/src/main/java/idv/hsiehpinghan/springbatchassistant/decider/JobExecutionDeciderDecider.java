package idv.hsiehpinghan.springbatchassistant.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionDeciderDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {
		String string = stepExecution.getExecutionContext().getString(
				"JobExecutionDecider_test");
		System.err.println("JobExecutionDeciderDecider string(" + string
				+ ") !!!");
		if ("JobExecutionDecider_test".equals(string) == true) {
			return new FlowExecutionStatus("JobExecutionDecider_test");
		}
		return FlowExecutionStatus.COMPLETED;
	}

}

package idv.hsiehpinghan.springbatchassistant.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class JobExecutionDeciderDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		System.err.println("JobExecutionDeciderDecider change exist code to JOB_EXECUTION_DECIDER_EXIT_CODE !!!");
		return new FlowExecutionStatus("JOB_EXECUTION_DECIDER_EXIT_CODE");
	}

}

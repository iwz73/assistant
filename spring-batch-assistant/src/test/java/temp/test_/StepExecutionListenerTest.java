package temp.test_;

import java.util.Collection;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import temp.configuration_.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class StepExecutionListenerTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("stepExecutionListenerJob")
	private Job stepExecutionListenerJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(stepExecutionListenerJob,
				builder.toJobParameters());
		assertBasicStepExecutions(jobExecution.getStepExecutions());
	}

	private void assertBasicStepExecutions(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertBasicStep_0Execution(stepExecutionArr[0]);
	}

	private void assertBasicStep_0Execution(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"UNKNOWN");
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.COMPLETED);
		assertBasicStep_0ExecutionContext(stepExecution.getExecutionContext());
	}

	private void assertBasicStep_0ExecutionContext(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.getString("beforeStep"),
				"beforeStep");
		Assert.assertEquals(executionContext.getString("afterStep"),
				"afterStep");
	}
}

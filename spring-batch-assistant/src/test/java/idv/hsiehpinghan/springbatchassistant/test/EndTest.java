package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

import java.util.Collection;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class EndTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("endJob")
	private Job endJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(endJob,
				builder.toJobParameters());
		assertEndJobExecution(jobExecution);
		assertEndStepExecutions(jobExecution.getStepExecutions());
	}

	private void assertEndJobExecution(JobExecution jobExecution) {
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(),
				"END TEST");
		Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
	}

	private void assertEndStepExecutions(
			Collection<StepExecution> stepExecutions) {
		StepExecution[] stepExecutionArr = new StepExecution[stepExecutions
				.size()];
		stepExecutions.toArray(stepExecutionArr);
		assertEndStep_0Execution(stepExecutionArr[0]);
	}

	private void assertEndStep_0Execution(StepExecution stepExecution) {
		Assert.assertEquals(stepExecution.getExitStatus().getExitCode(),
				"FAILED");
		Assert.assertEquals(stepExecution.getStatus(), BatchStatus.FAILED);
	}

}

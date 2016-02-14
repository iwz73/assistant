package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

import java.util.Collection;

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

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ChunkListenerTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("chunkListenerJob")
	private Job chunkListenerJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(chunkListenerJob,
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
		assertBasicStep_0ExecutionContext(stepExecution.getExecutionContext());
	}

	private void assertBasicStep_0ExecutionContext(
			ExecutionContext executionContext) {
		Assert.assertEquals(executionContext.getString("beforeChunk"),
				"beforeChunk");
		Assert.assertEquals(executionContext.getString("afterChunk"),
				"afterChunk");
		Assert.assertEquals(executionContext.getString("afterChunkError"),
				"afterChunkError");
	}
}

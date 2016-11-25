package idv.hsiehpinghan.springbatchassistant.test;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class EndFailStopTest extends AbstractTestNGSpringContextTests {
	private final String BATCH_STATUS_COMPLETED = "BATCH_STATUS_COMPLETED";
	private final String BATCH_STATUS_FAILED = "BATCH_STATUS_FAILED";
	private final String BATCH_STATUS_STOPPED = "BATCH_STATUS_STOPPED";

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("endFailStopJob")
	private Job job;

	@Test
	public void test() throws Exception {
		Assert.assertEquals(run(BATCH_STATUS_COMPLETED), ExitStatus.COMPLETED.getExitCode());
		Assert.assertEquals(run(BATCH_STATUS_FAILED), ExitStatus.FAILED.getExitCode());
		Assert.assertEquals(run(BATCH_STATUS_STOPPED), ExitStatus.STOPPED.getExitCode());
	}

	private String run(String batchStatus) throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("batchStatus", batchStatus);
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		return jobExecution.getExitStatus().getExitCode();
	}
}

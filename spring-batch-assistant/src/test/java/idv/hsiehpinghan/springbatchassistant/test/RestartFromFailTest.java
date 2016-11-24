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
public class RestartFromFailTest extends AbstractTestNGSpringContextTests {
	private final int START_COUNT = 1;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("restartFromFailJob")
	private Job job;

	@Test
	public void test() throws Exception {
		for (int i = 0; i < START_COUNT; ++i) {
			Assert.assertEquals(run(), ExitStatus.FAILED.getExitCode());
		}
		Assert.assertEquals(run(), ExitStatus.COMPLETED.getExitCode());
	}

	private String run() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		return jobExecution.getExitStatus().getExitCode();
	}
}

package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.suit.TestngSuitSetting;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpringBatchAssistantTest {
	private ApplicationContext applicationContext;
	private JobLauncher jobLauncher;
	private Job job1;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		jobLauncher = applicationContext.getBean(JobLauncher.class);
		job1 = applicationContext.getBean("job1", Job.class);
	}

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = jobLauncher.run(job1,
				builder.toJobParameters());
		Assert.assertEquals("COMPLETED", jobExecution.getExitStatus()
				.getExitCode());
	}

}

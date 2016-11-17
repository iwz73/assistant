package idv.hsiehpinghan.springbatchassistant.test_;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import temp.configuration_.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class AllowStartIfCompleteTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("allowStartIfCompleteJob")
	private Job allowStartIfCompleteJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		JobExecution jobExecution = null;
		jobExecution = jobLauncher.run(allowStartIfCompleteJob,
				builder.toJobParameters());
		Assert.assertEquals(jobExecution.getStepExecutions().size(), 1);
		jobExecution = jobLauncher.run(allowStartIfCompleteJob,
				builder.toJobParameters());
		Assert.assertEquals(jobExecution.getStepExecutions().size(), 1);
	}

}

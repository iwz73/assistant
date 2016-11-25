package idv.hsiehpinghan.springbatchassistant.assistant;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SpringBatchAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private SpringBatchAssistant assistant;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("springBatchAssistantJob")
	private Job job;

	@BeforeClass
	public void beforeClass() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
	}

	@Test
	public void getJobExecutionsByExitStatus() throws NoSuchJobException {
		List<JobExecution> jobExecutions = assistant.getJobExecutionsByExitStatus(ExitStatus.COMPLETED);
		Assert.assertEquals(jobExecutions.size(), 1);
	}

}

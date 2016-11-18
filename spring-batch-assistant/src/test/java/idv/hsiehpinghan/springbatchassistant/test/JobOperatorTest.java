package idv.hsiehpinghan.springbatchassistant.test;

import java.util.Iterator;
import java.util.Set;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JobOperatorTest extends AbstractTestNGSpringContextTests {
	@Autowired
	@Qualifier("jobOperatorJobLauncher")
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("jobOperatorJob")
	private Job job;
	@Autowired
	@Qualifier("jobOperatorjobOperator")
	private JobOperator jobOperator;

	@Test
	public void test() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		Thread.sleep(2000);
		Set<Long> RunningExecutions = jobOperator.getRunningExecutions(job.getName());
		Iterator<Long> iterator = RunningExecutions.iterator();
		while (iterator.hasNext()) {
			Long executionId = iterator.next();
			boolean isStopMessageSent = jobOperator.stop(executionId);
			System.err.println("stop message sent !!!");
			Assert.assertTrue(isStopMessageSent);
		}
		Thread.sleep(5000);
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(), ExitStatus.STOPPED.getExitCode());
	}
}

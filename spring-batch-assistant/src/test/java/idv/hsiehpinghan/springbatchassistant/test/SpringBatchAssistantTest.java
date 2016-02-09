package idv.hsiehpinghan.springbatchassistant.test;

import idv.hsiehpinghan.springbatchassistant.suit.TestngSuitSetting;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpringBatchAssistantTest {
	private ApplicationContext applicationContext;
//	private Job job;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
//		job = applicationContext.getBean(Job.class);
	}

	@Test
	public void test() throws Exception {
//		JobLauncher jobLauncher = new SimpleJobLauncher();
//		jobLauncher.run(job, null);
	}

}

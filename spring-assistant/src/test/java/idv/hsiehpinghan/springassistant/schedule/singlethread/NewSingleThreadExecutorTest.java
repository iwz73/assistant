package idv.hsiehpinghan.springassistant.schedule.singlethread;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springassistant.configuration.ScheduleSingleThreadSpringConfiguration;

@ContextConfiguration(classes = { ScheduleSingleThreadSpringConfiguration.class })
public class NewSingleThreadExecutorTest extends AbstractTestNGSpringContextTests {
	private final long SLEEP_MILISECONDS = 20000;

	@Test
	public void test() throws Exception {
		Thread.sleep(SLEEP_MILISECONDS);
	}
}

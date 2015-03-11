package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.rassistant.suit.TestngSuitSetting;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UniformDistributionAssistantTest {
	private UniformDistributionAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext
				.getBean(UniformDistributionAssistant.class);
	}

	@Test
	public void getStatistic() {
		Assert.assertEquals(0.1, assistant.getDensity(3, 0, 10));
		Assert.assertEquals(0.3, assistant.getDistribution(3, 0, 10));
	}
}

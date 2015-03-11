package idv.hsiehpinghan.pigassistant.assistant;

import idv.hsiehpinghan.pigassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PigAssistantTest {
	private PigAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(PigAssistant.class);
	}

	@Test
	public void loadFromHbase() throws Exception {
		assistant.loadFromHbase();

	}
}

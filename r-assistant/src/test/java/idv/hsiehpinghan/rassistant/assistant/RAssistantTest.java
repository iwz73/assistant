package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.rassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RAssistantTest {
	private RAssistant rAssistant;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		rAssistant = applicationContext.getBean(RAssistant.class);
	}

	@Test
	public void runScript() {
		File scriptFile = SystemResourceUtility
				.getFileResource("r_script/test.R");
		
		boolean result = rAssistant.runScript(scriptFile);
		Assert.assertEquals(result, true);
	}
}

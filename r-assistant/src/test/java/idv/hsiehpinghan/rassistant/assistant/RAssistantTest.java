package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.rassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.rosuda.JRI.REXP;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RAssistantTest {
	private RAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(RAssistant.class);
	}

	@Test
	public void runScript() {
		File scriptFile = SystemResourceUtility
				.getFileResource("r_script/rAssistantTest.R");
		File logFile = new File(FileUtils.getTempDirectory(), "rAssistantTest.log");
		REXP result = assistant.runScript(scriptFile, logFile);
		Assert.assertNotNull(result);
	}
}

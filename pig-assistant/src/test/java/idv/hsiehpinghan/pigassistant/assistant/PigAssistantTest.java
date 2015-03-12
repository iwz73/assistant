package idv.hsiehpinghan.pigassistant.assistant;

import java.io.File;

import idv.hsiehpinghan.pigassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.PrintUtility;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.introspect.BasicClassIntrospector.GetterMethodFilter;
import org.junit.Assert;
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
	public void downloadHbaseData() throws Exception {
		File targetDirectory = new File(FileUtils.getTempDirectory(),
				"downloadHbaseData");
		assistant.downloadHbaseData(targetDirectory);
		Assert.assertTrue(targetDirectory.list().length > 0);
//		FileUtils.deleteDirectory(targetDirectory);
	}
}

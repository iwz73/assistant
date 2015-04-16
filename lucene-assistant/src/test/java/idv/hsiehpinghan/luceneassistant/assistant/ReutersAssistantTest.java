package idv.hsiehpinghan.luceneassistant.assistant;

import idv.hsiehpinghan.luceneassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReutersAssistantTest {
	private ReutersAssistant reutersAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		reutersAssistant = applicationContext.getBean(ReutersAssistant.class);
	}

	@Test
	public void extractReuters() throws Exception {
		String reuters21578DirectoryPath = SystemResourceUtility
				.getFileResource("reuters21578/").getAbsolutePath();
		String outputDirectoryPath = reutersAssistant.extractReuters(
				reuters21578DirectoryPath, "/tmp/reuters21578/");
		File resultFile = new File(outputDirectoryPath);
		Assert.assertTrue(resultFile.list().length > 0);
	}
}

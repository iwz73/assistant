package idv.hsiehpinghan.hdfsassistant.utility;

import idv.hsiehpinghan.hdfsassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HdfsAssistantTest {
	private HdfsAssistant hdfsAssistant;
	private String hdfsFilePath;
	private File file_1;
	private String hdfsDirectoryPath;
	private File folder_1;
	private File tmp;
	private File testFile;
	private File testDir;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		setTestData(applicationContext);
	}

	@Test
	public void copyFromLocal() throws IOException {
		// File test.
		Assert.assertFalse(hdfsAssistant.exists(hdfsFilePath));
		hdfsAssistant.copyFromLocal(file_1, hdfsFilePath);
		Assert.assertTrue(hdfsAssistant.exists(hdfsFilePath));

		// Directory test.
		Assert.assertFalse(hdfsAssistant.exists(hdfsDirectoryPath));
		hdfsAssistant.copyFromLocal(folder_1, hdfsDirectoryPath);
		Assert.assertTrue(hdfsAssistant.exists(hdfsDirectoryPath));
	}

	@Test(dependsOnMethods = { "copyFromLocal" })
	public void copyToLocal() throws IOException {
		// File test.
		Assert.assertFalse(testFile.exists());
		hdfsAssistant.copyToLocal(hdfsFilePath, tmp);
		Assert.assertTrue(testFile.exists());

		// Directory test.
		Assert.assertFalse(testDir.exists());
		hdfsAssistant.copyToLocal(hdfsDirectoryPath, tmp);
		Assert.assertTrue(testDir.exists());
	}

	private void setTestData(ApplicationContext applicationContext)
			throws IOException {
		String userName = TestngSuitSetting.getUserName();
		file_1 = SystemResourceUtility.getFileResource("sample/file_1");
		hdfsFilePath = "/user/" + userName + "/test/hdfs/file_1";
		if (hdfsAssistant.exists(hdfsFilePath)) {
			hdfsAssistant.delete(hdfsFilePath);
		}
		folder_1 = SystemResourceUtility.getFileResource("sample/folder_1");
		hdfsDirectoryPath = "/user/" + userName + "/test/hdfs/folder_1";
		if (hdfsAssistant.exists(hdfsDirectoryPath)) {
			hdfsAssistant.delete(hdfsDirectoryPath);
		}

		tmp = FileUtils.getTempDirectory();
		testFile = new File(tmp, "file_1");
		if (testFile.exists()) {
			testFile.delete();
		}
		testDir = new File(tmp, "folder_1");
		if (testDir.exists()) {
			FileUtils.deleteDirectory(testDir);
		}
	}
}

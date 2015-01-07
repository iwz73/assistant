package idv.hsiehpinghan.hdfsassistant.utility;

import idv.hsiehpinghan.hdfsassistant.suit.TestngSuitSetting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.FileStatus;
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

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		String userName = TestngSuitSetting.getUserName();
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		file_1 = ResourceUtility.getFileResource("sample/file_1");
		hdfsFilePath = "hdfs://localhost/user/" + userName
				+ "/test/hdfs/file_1";
		if (hdfsAssistant.exists(hdfsFilePath)) {
			hdfsAssistant.delete(hdfsFilePath);
		}
		folder_1 = ResourceUtility.getFileResource("sample/folder_1");
		hdfsDirectoryPath = "hdfs://localhost/user/" + userName
				+ "/test/hdfs/folder_1";
		if (hdfsAssistant.exists(hdfsDirectoryPath)) {
			hdfsAssistant.delete(hdfsDirectoryPath);
		}
	}

	@Test
	public void writeHdfsFile() throws IllegalArgumentException, IOException {
		Assert.assertFalse(hdfsAssistant.exists(hdfsFilePath));
		hdfsAssistant.writeHdfsFile(hdfsFilePath, file_1);
		Assert.assertTrue(hdfsAssistant.exists(hdfsFilePath));
	}

	@Test(dependsOnMethods = { "writeHdfsFile" })
	public void copyHdfsFile() throws IllegalArgumentException, IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		hdfsAssistant.copyHdfsFile(hdfsFilePath, out);
		String actual = new String(out.toByteArray());
		String expected = FileUtils.readFileToString(file_1);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void writeHdfsDirectory() throws IOException {
		Assert.assertFalse(hdfsAssistant.exists(hdfsDirectoryPath));
		hdfsAssistant.writeHdfsDirectory(hdfsDirectoryPath, folder_1);
		FileStatus[] fss = hdfsAssistant.getFileStatuses(hdfsDirectoryPath);
		Assert.assertEquals(convertToSet(fss), convertToSet(folder_1.list()));
	}

	private Set<String> convertToSet(FileStatus[] fss) {
		Set<String> set = new HashSet<String>(fss.length);
		for (FileStatus fs : fss) {
			set.add(fs.getPath().getName());
		}
		return set;
	}

	private Set<String> convertToSet(String[] ss) {
		Set<String> set = new HashSet<String>(ss.length);
		for (String s : ss) {
			set.add(s);
		}
		return set;
	}
}

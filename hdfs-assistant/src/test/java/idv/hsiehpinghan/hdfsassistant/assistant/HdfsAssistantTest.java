package idv.hsiehpinghan.hdfsassistant.assistant;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hdfsassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class HdfsAssistantTest extends AbstractTestNGSpringContextTests {
	private String userName;
	private String hdfsFilePath;
	private String writeAndReadFilePath;
	private File file_1;
	private String hdfsDirectoryPath;
	private File folder_1;
	private File tmp;
	private File testFile;
	private File testDir;
	@Autowired
	private HdfsAssistant hdfsAssistant;
	@Autowired
	private Environment env;

	@BeforeClass
	public void beforeClass() throws Exception {
		setTestData(applicationContext);
	}

	@Test
	public void mkdir() throws Exception {
		String dir = "/user/" + userName + "/test/hdfs/dir1/dir2/dir3";
		boolean result = hdfsAssistant.mkdir(dir);
		Assert.assertTrue(result);
		Assert.assertTrue(hdfsAssistant.exists(dir));
	}

	@Test
	public void getWriter() throws Exception {
		SequenceFile.Writer writer = hdfsAssistant.getWriter(writeAndReadFilePath, Text.class, IntWritable.class);
		writer.append(new Text("key1"), new IntWritable(1));
		writer.append(new Text("key2"), new IntWritable(2));
		writer.append(new Text("key3"), new IntWritable(3));
		writer.close();
		Assert.assertTrue(hdfsAssistant.exists(writeAndReadFilePath));
	}

	@Test(dependsOnMethods = { "getWriter" })
	public void getReader() throws Exception {
		SequenceFile.Reader reader = hdfsAssistant.getReader(writeAndReadFilePath);
		Text key = new Text();
		IntWritable val = new IntWritable();
		int i = 1;
		while (reader.next(key, val)) {
			Assert.assertEquals(key.toString(), "key" + i);
			Assert.assertEquals(val.get(), i);
			++i;
		}
		reader.close();
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

	private void setTestData(ApplicationContext applicationContext) throws IOException {
		userName = env.getProperty("USER");
		writeAndReadFilePath = "/user/" + userName + "/test/hdfs/writeAndReadFile.seq";
		file_1 = applicationContext.getResource("classpath:sample/file_1").getFile();
		hdfsFilePath = "/user/" + userName + "/test/hdfs/file_1";
		if (hdfsAssistant.exists(hdfsFilePath)) {
			hdfsAssistant.delete(hdfsFilePath);
		}
		folder_1 = applicationContext.getResource("classpath:sample/folder_1").getFile();
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

package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;
import idv.hsiehpinghan.mahoutassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SequenceFilesAssistantTest {
	private String sequenceFilesAssistantDirectoryPath;
	private HdfsAssistant hdfsAssistant;
	private SequenceFileAssistant sequenceFileAssistant;
	private String resultHdfsFile;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		sequenceFileAssistant = applicationContext
				.getBean(SequenceFileAssistant.class);
		init();
	}

	@Test
	public void writeDirectoryToSequenceFile() throws Exception {
		String testName = "writeDirectoryToSequenceFile";
		String hdfsOutputFilePath = removeAndGetHdfsDirectoryPath(testName)
				+ "hdfsOutputFile.seq";
		resultHdfsFile = sequenceFileAssistant.writeDirectoryToSequenceFile(
				SystemResourceUtility.getFileResource("sample")
						.getAbsolutePath(), hdfsOutputFilePath);
		Assert.assertTrue(hdfsAssistant.exists(resultHdfsFile));
	}

	@Test(dependsOnMethods = { "writeDirectoryToSequenceFile" })
	public void readSequenceFile() throws Exception {
		String testName = "readSequenceFile";
		SequenceFile.Reader reader = sequenceFileAssistant
				.readSequenceFile(resultHdfsFile);
		Writable key = (Writable) ReflectionUtils.newInstance(
				reader.getKeyClass(), hdfsAssistant.getHdfsConfiguration());
		Writable value = (Writable) ReflectionUtils.newInstance(
				reader.getValueClass(), hdfsAssistant.getHdfsConfiguration());
		int i = 0;
		System.out.println(testName + ":");
		while (reader.next(key, value)) {
			System.out.printf("[%s] %s %s \n", reader.getPosition(), key,
					value.getClass());
			++i;
		}
		Assert.assertEquals(3, i);
	}

	private void init() throws IOException {
		// Create hdfs directory
		String userName = TestngSuitSetting.getUserName();
		sequenceFilesAssistantDirectoryPath = "/user/" + userName
				+ "/test/mahout/sequence_file_assistant";
		if (hdfsAssistant.exists(sequenceFilesAssistantDirectoryPath) == false) {
			hdfsAssistant.mkdir(sequenceFilesAssistantDirectoryPath);
		}
	}

	private String removeAndGetHdfsDirectoryPath(String testName)
			throws IOException {
		String hdfsDirectoryPath = sequenceFilesAssistantDirectoryPath + "/"
				+ testName + "/";
		if (hdfsAssistant.exists(hdfsDirectoryPath)) {
			hdfsAssistant.removeDirectory(hdfsDirectoryPath);
		}
		hdfsAssistant.mkdir(hdfsDirectoryPath);
		return hdfsDirectoryPath;
	}
}

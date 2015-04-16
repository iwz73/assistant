package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;
import idv.hsiehpinghan.luceneassistant.assistant.ReutersAssistant;
import idv.hsiehpinghan.mahoutassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SequenceFilesAssistantTest {
	private String sequenceFilesAssistantDirectoryPath;
	private SequenceFilesAssistant sequenceFilesAssistant;
	private HdfsAssistant hdfsAssistant;
	
	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		sequenceFilesAssistant = applicationContext
				.getBean(SequenceFilesAssistant.class);
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		init();
	}

	@Test
	public void sequenceFilesFromDirectory() throws Exception {
//		String testName = "sequenceFilesFromDirectory";
//		String inputDirectory = SystemResourceUtility
//				.getFileResource("sample/").getAbsolutePath();
//		String hdfsOutputDirPath = sequenceFilesAssistant.sequenceFilesFromDirectory(inputDirectory, removeAndGetHdfsDirectoryPath(testName));
//		
//		System.err.println(hdfsOutputDirPath);
		
		
	}

	private void init() throws IOException {
		// Create hdfs directory
		String userName = TestngSuitSetting.getUserName();
		sequenceFilesAssistantDirectoryPath = "/user/" + userName
				+ "/test/mahout/sequence_files_assistant";
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

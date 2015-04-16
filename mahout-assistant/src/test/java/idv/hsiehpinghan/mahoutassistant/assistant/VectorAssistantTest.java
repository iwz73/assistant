package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;
import idv.hsiehpinghan.mahoutassistant.suit.TestngSuitSetting;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VectorAssistantTest {
	private String vectorAssistantDirectoryPath;
	private HdfsAssistant hdfsAssistant;
	private VectorAssistant vectorAssistant;
	private SequenceFileAssistant sequenceFileAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		vectorAssistant = applicationContext.getBean(VectorAssistant.class);
		sequenceFileAssistant = applicationContext
				.getBean(SequenceFileAssistant.class);
	}

	@Test
	public void transformSequenceFileToSparseVectors() throws Exception {
		// String hdfsOutputDirectory =
		// vectorAssistant.transformSequenceFileToSparseVectors(hdfsSequenceFileDirecotory,
		// "");

	}

	private void init() throws IOException {
		// Create hdfs directory
		String userName = TestngSuitSetting.getUserName();
		vectorAssistantDirectoryPath = "/user/" + userName
				+ "/test/mahout/vector_assistant";
		if (hdfsAssistant.exists(vectorAssistantDirectoryPath) == false) {
			hdfsAssistant.mkdir(vectorAssistantDirectoryPath);
		}
	}

	private String removeAndGetHdfsDirectoryPath(String testName)
			throws IOException {
		String hdfsDirectoryPath = vectorAssistantDirectoryPath + "/"
				+ testName + "/";
		if (hdfsAssistant.exists(hdfsDirectoryPath)) {
			hdfsAssistant.removeDirectory(hdfsDirectoryPath);
		}
		hdfsAssistant.mkdir(hdfsDirectoryPath);
		return hdfsDirectoryPath;
	}
}

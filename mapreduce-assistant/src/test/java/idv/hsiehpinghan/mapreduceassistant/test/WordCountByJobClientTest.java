package idv.hsiehpinghan.mapreduceassistant.test;


import idv.hsiehpinghan.mapreduceassistant.suit.TestngSuitSetting;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WordCountByJobClientTest {
	private ApplicationContext applicationContext;
	
	@BeforeClass
	public void beforeClass() throws IOException {
		applicationContext = TestngSuitSetting
				.getApplicationContext();
	}
	
	@Test
	public void test() throws Exception {
		String inputPath = "/home/thank/git/assistant/mapreduce-assistant/src/test/file/WordCount_*.txt";
		String outputPath = "/tmp/WordCountByJobClient";
		FileUtils.deleteDirectory(new File(outputPath));
		WordCountByJobClient wordCount = applicationContext.getBean(WordCountByJobClient.class);
		wordCount.count(inputPath, outputPath);
	}

}

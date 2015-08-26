package idv.hsiehpinghan.mapreduceassistant.test;


import idv.hsiehpinghan.mapreduceassistant.suit.TestngSuitSetting;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WordTest {
	private ApplicationContext applicationContext;
	
	@BeforeClass
	public void beforeClass() throws IOException {
		applicationContext = TestngSuitSetting
				.getApplicationContext();
	}
	
	@Test
	public void test() throws Exception {
		String inputPath = "/home/hsiehpinghan/workspace/mapreduce-assistant/src/test/file/WordTest.txt";
		String outputPath = "/tmp/WordTest";
		FileUtils.deleteDirectory(new File(outputPath));
		WordCount wordCount = applicationContext.getBean(WordCount.class);
		Assert.assertTrue(wordCount.count(inputPath, outputPath));
	}

}

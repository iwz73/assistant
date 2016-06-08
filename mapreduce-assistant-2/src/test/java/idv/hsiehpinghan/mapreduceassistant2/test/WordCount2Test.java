package idv.hsiehpinghan.mapreduceassistant2.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.mapreduceassistant2.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class WordCount2Test extends AbstractTestNGSpringContextTests {
	@Autowired
	private WordCount2 wordCount2;

	@Test
	public void test() throws Exception {
		String inputPath = "/home/thank/git/assistant/mapreduce-assistant-2/src/test/file/WordCount_*.txt";
		String outputPath = "/tmp/WordCount";
		FileUtils.deleteDirectory(new File(outputPath));
		Assert.assertTrue(wordCount2.count());
	}

}

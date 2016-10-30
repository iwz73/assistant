package idv.hsiehpinghan.hanlpassistant.tokenizer.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hanlpassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class CoreSynonymDictionaryAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private CoreSynonymDictionaryAssistant assistant;

	@Test
	public void distance() {
		Assert.assertEquals(assistant.distance("香蕉", "苹果"), 19980);
		Assert.assertEquals(assistant.distance("香蕉", "自行车"), 1854515628);
	}
}

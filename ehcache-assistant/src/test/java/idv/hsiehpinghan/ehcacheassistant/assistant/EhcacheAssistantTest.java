package idv.hsiehpinghan.ehcacheassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.ehcacheassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class EhcacheAssistantTest extends AbstractTestNGSpringContextTests {
	private final String KEY = "key";
	private final String VALUE = "value";

	@Autowired
	private EhcacheAssistant assistant;

	@Test
	public void putAndGetTest() {
		assistant.put(KEY, VALUE);
		String value = (String) assistant.get(KEY);
		Assert.assertEquals(value, VALUE);
	}
}

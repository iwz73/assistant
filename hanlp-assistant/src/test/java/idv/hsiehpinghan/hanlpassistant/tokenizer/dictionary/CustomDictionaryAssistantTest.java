package idv.hsiehpinghan.hanlpassistant.tokenizer.dictionary;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CoreDictionary;

import idv.hsiehpinghan.hanlpassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class CustomDictionaryAssistantTest extends AbstractTestNGSpringContextTests {
	private static final String PROGRAMMER = "攻城狮";
	private static final String PROGRAMMER_ATTRIBUTE = "nz 1";
	private static final String TEXT = "攻城狮逆袭单身狗，迎娶白富美，走上人生巅峰";
	@Autowired
	private CustomDictionaryAssistant assistant;

	@Test
	public void add() {
		boolean result = false;
		result = assistant.add(PROGRAMMER, PROGRAMMER_ATTRIBUTE);
		Assert.assertTrue(result);
		result = assistant.add("单身狗", "nz 1024 n 1");
		Assert.assertTrue(result);
	}

	@Test
	public void insert() {
		boolean result = assistant.insert("白富美", "nz 1024");
		Assert.assertTrue(result);
	}

	@Test(dependsOnMethods = { "add" })
	public void get() {
		CoreDictionary.Attribute attribute = assistant.get(PROGRAMMER);
		Assert.assertEquals(attribute.toString().trim(), PROGRAMMER_ATTRIBUTE);
	}

	@Test(dependsOnMethods = { "add" })
	public void remove() {
		assistant.remove(PROGRAMMER);
	}

	@Test(dependsOnMethods = { "insert", "remove" })
	public void parseText() {
		Assert.assertEquals(assistant.parseText(TEXT).toString(),
				"[单身狗 nz 1024 n 1, 白富美 nz 1024, 攻城 vi 15, 逆袭 nz 199]");
	}

	@Test(dependsOnMethods = { "insert" })
	public void getSearcher() {
		BaseSearcher<CoreDictionary.Attribute> baseSearcher = assistant.getSearcher(TEXT);
		Map.Entry<String, CoreDictionary.Attribute> entry;
		int i = 0;
		while ((entry = baseSearcher.next()) != null) {
			switch (i) {
			case 0:
				Assert.assertEquals(entry.toString().trim(), "攻城狮=nz 1");
				break;
			case 1:
				Assert.assertEquals(entry.toString().trim(), "单身狗=nz 1024 n 1");
				break;
			case 2:
				Assert.assertEquals(entry.toString().trim(), "白富美=nz 1024");
				break;
			default:
				throw new RuntimeException("i(" + i + ") not implements !!!");
			}
			++i;
		}
	}

}

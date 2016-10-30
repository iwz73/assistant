package idv.hsiehpinghan.hanlpassistant.suggester.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hanlpassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SuggesterAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private SuggesterAssistant assistant;

	@BeforeClass
	private void beforeClass() {
		String[] sentences = new String[] { "威廉王子发表演说 呼吁保护野生动物", "《时代》年度人物最终入围名单出炉 普京马云入选", "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散",
				"日本保密法将正式生效 日媒指其损害国民知情权", "英报告说空气污染带来“公共健康危机”" };
		assistant.addSentence(sentences);
	}

	@Test
	public void suggest() {
		Assert.assertEquals(assistant.suggest("发言", 1).toString(), "[威廉王子发表演说 呼吁保护野生动物]"); // 语义
		Assert.assertEquals(assistant.suggest("危机公共", 1).toString(), "[英报告说空气污染带来“公共健康危机”]"); // 字符
		Assert.assertEquals(assistant.suggest("mayun", 1).toString(), "[《时代》年度人物最终入围名单出炉 普京马云入选]"); // 拼音
	}
}

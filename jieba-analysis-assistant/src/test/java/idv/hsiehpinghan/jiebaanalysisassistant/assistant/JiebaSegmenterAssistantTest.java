package idv.hsiehpinghan.jiebaanalysisassistant.assistant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

import idv.hsiehpinghan.jiebaanalysisassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JiebaSegmenterAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JiebaSegmenterAssistant assistant;

	@Test
	public void getSegTokens() {
		String text = "这是一个伸手不见五指的黑夜。";
		testIndexMode(text);
		testSearchMode(text);
	}

	private void testIndexMode(String text) {
		List<SegToken> segTokens = assistant.getSegTokens(text, SegMode.INDEX);
		for (SegToken segToken : segTokens) {
			System.err.println(segToken);
		}
		Assert.assertEquals(segTokens.size(), 9);
	}

	private void testSearchMode(String text) {
		List<SegToken> segTokens = assistant.getSegTokens(text, SegMode.SEARCH);
		for (SegToken segToken : segTokens) {
			System.err.println(segToken);
		}
		Assert.assertEquals(segTokens.size(), 6);
	}
}

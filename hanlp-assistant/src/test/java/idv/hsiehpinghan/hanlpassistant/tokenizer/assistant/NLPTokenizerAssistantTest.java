package idv.hsiehpinghan.hanlpassistant.tokenizer.assistant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import idv.hsiehpinghan.hanlpassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class NLPTokenizerAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private NLPTokenizerAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		HanLP.Config.enableDebug();
	}

	@Test
	public void segment() {
		List<Term> terms = assistant.segment("他说的确实在理");
		Assert.assertEquals(terms.toString(), "[他/r, 说/v, 的/uj, 确实/ad, 在理/a]");
	}
}

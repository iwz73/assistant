package idv.hsiehpinghan.hanlpassistant.segment.assistant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hankcs.hanlp.seg.common.Term;

import idv.hsiehpinghan.hanlpassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class NShortSegmentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private NShortSegmentAssistant assistant;

	@Test
	public void segment() {
		List<Term> terms = assistant.segment("今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。");
		Assert.assertEquals(terms.toString(),
				"[今天/t, ，/w, 刘志军/nr, 案/ng, 的/uj, 关键/n, 人物/n, ,/w, 山西/ns, 女/b, 商人/n, 丁书苗/nr, 在/p, 市二中院/nt, 出庭/v, 受审/v, 。/w]");
	}
}
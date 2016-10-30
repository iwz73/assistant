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
public class NameRecognizeSegmentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private NameRecognizeSegmentAssistant assistant;

	@Test
	public void segment() {
		List<Term> terms = assistant.segment("签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。");
		Assert.assertEquals(terms.toString(),
				"[签约/v, 仪式/n, 前/f, ，/w, 秦光荣/nr, 、/w, 李纪恒/nr, 、/w, 仇和/nr, 等/u, 一同/d, 会见/v, 了/ul, 参加/v, 签约/v, 的/uj, 企业家/n, 。/w]");
	}
}
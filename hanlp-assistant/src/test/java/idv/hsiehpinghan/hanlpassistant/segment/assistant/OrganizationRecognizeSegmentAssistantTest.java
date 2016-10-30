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
public class OrganizationRecognizeSegmentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private OrganizationRecognizeSegmentAssistant assistant;

	@Test
	public void segment() {
		List<Term> terms = assistant.segment("偶尔去地中海影城看电影。");
		Assert.assertEquals(terms.toString(), "[偶尔/d, 去/v, 地中海影城/nt, 看/v, 电影/n, 。/w]");
	}
}
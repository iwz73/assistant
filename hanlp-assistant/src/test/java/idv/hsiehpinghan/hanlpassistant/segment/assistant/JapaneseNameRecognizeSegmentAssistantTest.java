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
public class JapaneseNameRecognizeSegmentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JapaneseNameRecognizeSegmentAssistant assistant;

	@Test
	public void segment() {
		List<Term> terms = assistant.segment("北川景子参演了林诣彬导演的《速度与激情3》");
		Assert.assertEquals(terms.toString(),
				"[北川景子/nrj, 参演/v, 了/ul, 林诣彬/nr, 导演/n, 的/uj, 《/w, 速度/n, 与/p, 激情/n, 3/m, 》/w]");
	}
}
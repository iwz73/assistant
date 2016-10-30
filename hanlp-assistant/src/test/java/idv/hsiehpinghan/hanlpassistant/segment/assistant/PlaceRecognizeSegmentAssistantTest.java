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
public class PlaceRecognizeSegmentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private PlaceRecognizeSegmentAssistant assistant;

	@Test
	public void segment() {
		List<Term> terms = assistant.segment("蓝翔给宁夏固原市彭阳县红河镇黑牛沟村捐赠了挖掘机");
		Assert.assertEquals(terms.toString(),
				"[蓝翔/nr, 给/p, 宁夏/ns, 固原市/ns, 彭阳县/ns, 红河/nz, 镇/n, 黑牛沟村/ns, 捐赠/v, 了/ul, 挖掘机/n]");
	}
}
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
public class TranslatedNameRecognizeSegmentAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private TranslatedNameRecognizeSegmentAssistant assistant;

	@Test
	public void segment() {
		List<Term> terms = assistant
				.segment("一桶冰水当头倒下，微软的比尔盖茨、Facebook的扎克伯格跟桑德博格、亚马逊的贝索斯、苹果的库克全都不惜湿身入镜，这些硅谷的科技人，飞蛾扑火似地牺牲演出，其实全为了慈善。");
		Assert.assertEquals(terms.toString(),
				"[一桶/nz, 冰水/n, 当头/d, 倒下/v, ，/w, 微软/nt, 的/uj, 比尔盖茨/nrf, 、/w, Facebook/nx, 的/uj, 扎克伯格/nrf, 跟/p, 桑德博格/nrf, 、/w, 亚马逊/nrf, 的/uj, 贝索斯/nrf, 、/w, 苹果/n, 的/uj, 库克/nr, 全都/d, 不惜/v, 湿身/nz, 入镜/nz, ，/w, 这些/r, 硅谷/n, 的/uj, 科技/n, 人/n, ，/w, 飞蛾/n, 扑火/v, 似/vg, 地/uv, 牺牲/v, 演出/v, ，/w, 其实/d, 全/a, 为了/p, 慈善/a, 。/w]");
	}
}
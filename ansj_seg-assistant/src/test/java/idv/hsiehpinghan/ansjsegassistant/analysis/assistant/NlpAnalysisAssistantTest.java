package idv.hsiehpinghan.ansjsegassistant.analysis.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.ansjsegassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class NlpAnalysisAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private NlpAnalysisAssistant assistant;

	@Test
	public void parse() {
		Assert.assertEquals(
				assistant.parse("2013年特偵組指控立法院長王金平替民進黨立委柯建銘關說，卻扯出檢察官林秀濤遭監聽，林因此向檢察總長黃世銘等4人提告求償，北院今天判決黃世銘賠償30萬元，其餘駁回。")
						.toString(),
				"[2013年/m,特偵/nw,組,指控/v,立法院/nw,長/nr,王金平/nr,替/p,民進/nr,黨立委/nw,柯建銘/nr,關說/nr,，/w,卻扯/nw,出/v,檢察官/nw,林秀濤/nr,遭/v,監聽/nr,，/w,林因/nw,此/r,向/p,檢察/nw,總長/nr,黃世銘/nr,等/u,4/m,人/n,提/v,告/v,求/v,償,，/w,北院/nw,今天/t,判決/nw,黃世銘/nr,賠償/nw,30萬元/m,，/w,其/r,餘駁/nw,回/v,。/w]");
	}

}

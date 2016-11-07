package idv.hsiehpinghan.ansjsegassistant.analysis.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.ansjsegassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ToAnalysisAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ToAnalysisAssistant assistant;

	@Test
	public void parse() {
		Assert.assertEquals(
				assistant.parse("2013年特偵組指控立法院長王金平替民進黨立委柯建銘關說，卻扯出檢察官林秀濤遭監聽，林因此向檢察總長黃世銘等4人提告求償，北院今天判決黃世銘賠償30萬元，其餘駁回。")
						.toString(),
				"2013年/m,特/d,偵,組,指控/v,立法/vn,院/n,長/nr,王金平/nr,替/p,民/ng,進/nr,黨,立委柯/nr,建/v,銘/nr,關/nr,說,，/w,卻,扯/v,出/v,檢,察/j,官林秀/nr,濤,遭/v,監,聽/nr,，/w,林/ng,因/p,此/r,向/p,檢,察/j,總,長/nr,黃世銘/nr,等/u,4/m,人/n,提/v,告/v,求/v,償,，/w,北/f,院/n,今天/t,判/v,決,黃世銘/nr,賠,償,30/m,萬/nr,元/q,，/w,其/r,餘,駁,回/v,。/w");
	}

}

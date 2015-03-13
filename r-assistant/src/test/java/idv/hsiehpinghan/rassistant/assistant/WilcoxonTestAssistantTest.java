package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.rassistant.result.WilcoxonTestResult;
import idv.hsiehpinghan.rassistant.suit.TestngSuitSetting;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WilcoxonTestAssistantTest {
	private WilcoxonTestAssistant assistant;
	private String[] values = new String[] { "843", "856", "870", "858", "823",
			"836", "880", "861" };

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(WilcoxonTestAssistant.class);
	}

	@Test
	public void getResult1() {
		WilcoxonTestResult result = assistant.getResult1(values, "850");
		String expected = "[statistic=22.0,pValue=0.6406250000000001,hypothesizedMedian=850.0,testType=TWO_TAIL,method=Wilcoxon signed rank test,confidenceInterval=836.0,sampleMedian=854.5]";
		Assert.assertTrue(result.toString().endsWith(expected));
	}

}

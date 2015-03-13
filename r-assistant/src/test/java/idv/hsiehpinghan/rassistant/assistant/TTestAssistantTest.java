package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.rassistant.enumeration.HypothesisTestType;
import idv.hsiehpinghan.rassistant.result.TTestResult;
import idv.hsiehpinghan.rassistant.suit.TestngSuitSetting;
import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TTestAssistantTest {
	private TTestAssistant assistant;
	private String[] values = new String[] { "1200", "1175", "1080", "1275",
			"1201", "1387", "1090", "1280", "1400", "1287", "1225" };
	
	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(TTestAssistant.class);
	}

	@Test
	public void getResult2() {
		TTestResult result = assistant.getResult2(values, "1160",
				HypothesisTestType.RIGHT_TAIL);
		String expected = "[statistic=2.439702846246465,degreeOfFreedom=10.0,pValue=0.01743334637585949,confidenceInterval=1179.6329069149415,sampleMean=1236.3636363636363,hypothesizedMean=1160.0,testType=RIGHT_TAIL,method=One Sample t-test]";
		Assert.assertTrue(result.toString().endsWith(expected));
	}

}

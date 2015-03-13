package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.StringUtility;
import idv.hsiehpinghan.rassistant.enumeration.HypothesisTestType;
import idv.hsiehpinghan.rassistant.result.WilcoxonTestResult;

import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WilcoxonTestAssistant extends TestAssistantBase {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	public static final String COMMA_STRING = StringUtility.COMMA_STRING;

	@Autowired
	private Rengine rengine;

	public WilcoxonTestResult getResult1(String[] values,
			String hypothesizedMedian) {
		return getResult(values, hypothesizedMedian, null);
	}

	private WilcoxonTestResult getResult(String[] values,
			String hypothesizedMedian, HypothesisTestType testType) {
		StringBuilder sb = new StringBuilder();
		sb.append("x <- c(");
		sb.append(ArrayUtility.toString(values, COMMA_STRING));
		sb.append(");");
		rengine.eval(sb.toString());

		sb.setLength(0);
		sb.append("htest <- wilcox.test(x,conf.int=TRUE");
		if (hypothesizedMedian != null) {
			sb.append(",mu=");
			sb.append(hypothesizedMedian);
		}
		if (testType != null) {
			sb.append(getHypothesisTestString(testType));
		}
		sb.append(");");
		rengine.eval(sb.toString());
		return generateResult();
	}

	private WilcoxonTestResult generateResult() {
		double statistic = rengine.eval("htest$statistic").asDouble();
		double pValue = rengine.eval("htest$p.value").asDouble();
		double hypothesizedMedian = rengine.eval("htest$null.value").asDouble();
		HypothesisTestType testType = getHypothesisTestType(rengine.eval(
				"htest$alternative").asString());
		String method = rengine.eval("htest$method").asString();
		double confidenceInterval = rengine.eval("htest$conf.int").asDouble();
		double sampleMedian = rengine.eval("htest$estimate").asDouble();
		return new WilcoxonTestResult(statistic, pValue, hypothesizedMedian,
				testType, method, confidenceInterval, sampleMedian);
	}

}
package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.StringUtility;
import idv.hsiehpinghan.rassistant.enumeration.HypothesisTestType;
import idv.hsiehpinghan.rassistant.result.TTestResult;

import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TTestAssistant extends TestAssistantBase {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	public static final String COMMA_STRING = StringUtility.COMMA_STRING;

	@Autowired
	private Rengine rengine;

	public TTestResult getResult1(String[] values, String hypothesizedMean) {
		return getResult(values, hypothesizedMean, null, null);
	}

	public TTestResult getResult2(String[] values, String hypothesizedMean,
			HypothesisTestType testType) {
		return getResult(values, hypothesizedMean, null, testType);
	}

	public TTestResult getResult3(String[] values, String confidenceInterval) {
		return getResult(values, null, confidenceInterval, null);
	}

	private TTestResult getResult(String[] values, String hypothesizedMean,
			String confidenceInterval, HypothesisTestType testType) {
		StringBuilder sb = new StringBuilder();
		sb.append("x <- c(");
		sb.append(ArrayUtility.toString(values, COMMA_STRING));
		sb.append(");");
		rengine.eval(sb.toString());

		sb.setLength(0);
		sb.append("htest <- t.test(x");
		if (hypothesizedMean != null) {
			sb.append(",mu=");
			sb.append(hypothesizedMean);
		}
		if (confidenceInterval != null) {
			sb.append(",conf.level=");
			sb.append(confidenceInterval);
		}
		if (testType != null) {
			sb.append(getHypothesisTestString(testType));
		}
		sb.append(");");
		rengine.eval(sb.toString());
		return generateResult();
	}

	private TTestResult generateResult() {
		double statistic = rengine.eval("htest$statistic").asDouble();
		double degreeOfFreedom = rengine.eval("htest$parameter").asDouble();
		double pValue = rengine.eval("htest$p.value").asDouble();
		double confidenceInterval = rengine.eval("htest$conf.int").asDouble();
		double sampleMean = rengine.eval("htest$estimate").asDouble();
		double hypothesizedMean = rengine.eval("htest$null.value").asDouble();
		HypothesisTestType testType = getHypothesisTestType(rengine.eval(
				"htest$alternative").asString());
		String method = rengine.eval("htest$method").asString();
		return new TTestResult(statistic, degreeOfFreedom, pValue,
				confidenceInterval, sampleMean, hypothesizedMean, testType,
				method);
	}

}
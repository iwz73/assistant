package idv.hsiehpinghan.rassistant.assistant;

import idv.hsiehpinghan.rassistant.enumeration.HypothesisTestType;

public abstract class TestAssistantBase {
	protected HypothesisTestType getHypothesisTestType(String testType) {
		switch (testType) {
		case "less":
			return HypothesisTestType.LEFT_TAIL;
		case "two.sided":
			return HypothesisTestType.TWO_TAIL;
		case "greater":
			return HypothesisTestType.RIGHT_TAIL;
		default:
			throw new RuntimeException("Test type(" + testType
					+ ") undefined !!!");
		}
	}

	protected String getHypothesisTestString(HypothesisTestType testType) {
		if (HypothesisTestType.LEFT_TAIL.equals(testType)) {
			return ",alternative=\"less\"";
		} else if (HypothesisTestType.TWO_TAIL.equals(testType)) {
			return ",alternative=\"two.sided\"";
		} else if (HypothesisTestType.RIGHT_TAIL.equals(testType)) {
			return ",alternative=\"greater\"";
		} else {
			throw new RuntimeException("Hypothesis test type(" + testType
					+ ") undefined !!!");
		}
	}
}

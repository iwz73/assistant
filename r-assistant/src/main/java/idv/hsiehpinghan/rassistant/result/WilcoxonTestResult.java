package idv.hsiehpinghan.rassistant.result;

import idv.hsiehpinghan.rassistant.enumeration.HypothesisTestType;

public class WilcoxonTestResult extends ResultBase {
	private double statistic;
	private double pValue;
	private double hypothesizedMedian;
	private HypothesisTestType testType;
	private String method;
	private double confidenceInterval;
	private double sampleMedian;

	public WilcoxonTestResult(double statistic, double pValue,
			double hypothesizedMedian, HypothesisTestType testType,
			String method, double confidenceInterval, double sampleMedian) {
		super();
		this.statistic = statistic;
		this.pValue = pValue;
		this.hypothesizedMedian = hypothesizedMedian;
		this.testType = testType;
		this.method = method;
		this.confidenceInterval = confidenceInterval;
		this.sampleMedian = sampleMedian;
	}

	public double getStatistic() {
		return statistic;
	}

	public void setStatistic(double statistic) {
		this.statistic = statistic;
	}

	public double getpValue() {
		return pValue;
	}

	public void setpValue(double pValue) {
		this.pValue = pValue;
	}

	public double getHypothesizedMedian() {
		return hypothesizedMedian;
	}

	public void setHypothesizedMedian(double hypothesizedMedian) {
		this.hypothesizedMedian = hypothesizedMedian;
	}

	public HypothesisTestType getTestType() {
		return testType;
	}

	public void setTestType(HypothesisTestType testType) {
		this.testType = testType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public double getConfidenceInterval() {
		return confidenceInterval;
	}

	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}

	public double getSampleMedian() {
		return sampleMedian;
	}

	public void setSampleMedian(double sampleMedian) {
		this.sampleMedian = sampleMedian;
	}

}

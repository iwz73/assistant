package idv.hsiehpinghan.rassistant.result;

import idv.hsiehpinghan.rassistant.enumeration.HypothesisTestType;

public class TTestResult extends ResultBase {
	private double statistic;
	private double degreeOfFreedom;
	private double pValue;
	private double confidenceInterval;
	private double sampleMean;
	private double hypothesizedMean;
	private HypothesisTestType testType;
	private String method;

	public TTestResult(double statistic, double degreeOfFreedom, double pValue,
			double confidenceInterval, double sampleMean,
			double hypothesizedMean, HypothesisTestType testType, String method) {
		super();
		this.statistic = statistic;
		this.degreeOfFreedom = degreeOfFreedom;
		this.pValue = pValue;
		this.confidenceInterval = confidenceInterval;
		this.sampleMean = sampleMean;
		this.hypothesizedMean = hypothesizedMean;
		this.testType = testType;
		this.method = method;
	}

	public double getStatistic() {
		return statistic;
	}

	public void setStatistic(double statistic) {
		this.statistic = statistic;
	}

	public double getDegreeOfFreedom() {
		return degreeOfFreedom;
	}

	public void setDegreeOfFreedom(double degreeOfFreedom) {
		this.degreeOfFreedom = degreeOfFreedom;
	}

	public double getpValue() {
		return pValue;
	}

	public void setpValue(double pValue) {
		this.pValue = pValue;
	}

	public double getConfidenceInterval() {
		return confidenceInterval;
	}

	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}

	public double getSampleMean() {
		return sampleMean;
	}

	public void setSampleMean(double sampleMean) {
		this.sampleMean = sampleMean;
	}

	public double getHypothesizedMean() {
		return hypothesizedMean;
	}

	public void setHypothesizedMean(double hypothesizedMean) {
		this.hypothesizedMean = hypothesizedMean;
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

}

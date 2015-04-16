package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;
import idv.hsiehpinghan.mahoutassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClusterAssistantTest {
	private final double[][] POINTS = { { 1, 1 }, { 2, 1 }, { 1, 2 }, { 2, 2 },
			{ 3, 3 }, { 8, 8 }, { 9, 8 }, { 8, 9 }, { 9, 9 } };
	private final int NUMBER_OF_CLUSTERS = 2;
	private String clusterAssistantDirectoryPath;
	private HdfsAssistant hdfsAssistant;
	private ClusterAssistant clusterAssistant;
	private SequenceFilesAssistant sequenceFilesAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		clusterAssistant = applicationContext.getBean(ClusterAssistant.class);
		sequenceFilesAssistant = applicationContext
				.getBean(SequenceFilesAssistant.class);
		init();
	}

	@Test
	public void runEuclideanDistanceMeasureKMeans() throws Exception {
		String testName = "runEuclideanDistanceMeasureKMeans";
		List<Vector> points = getPoints(POINTS);
		List<Vector> initialPoints = getInitialPoints(points,
				NUMBER_OF_CLUSTERS);
		String hdfsPointsFilePath = deleteAndGetHdfsPointsFilePath(testName);
		String hdfsClustersDirectoryPath = removeAndGetHdfsClustersDirectoryPath(testName);
		String hdfsOutputDirectoryPath = removeAndGetHdfsOutputDirectoryPath(testName);
		double convergenceDelta = 0.001;
		int maxIterations = 10;
		boolean runClustering = true;
		double clusterClassificationThreshold = 0;
		boolean runSequential = false;
		String pointsSeqenceFilePath = sequenceFilesAssistant
				.writePointsToFile(points, hdfsPointsFilePath);
		String resultFilePath = clusterAssistant
				.runEuclideanDistanceMeasureKMeans(initialPoints,
						pointsSeqenceFilePath, hdfsClustersDirectoryPath,
						hdfsOutputDirectoryPath, convergenceDelta,
						maxIterations, runClustering,
						clusterClassificationThreshold, runSequential);
		SequenceFile.Reader reader = hdfsAssistant.getReader(resultFilePath);
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
		int i = 0;
		System.out.println(testName + ":");
		while (reader.next(key, value)) {
			System.out.println(key.toString() + "/" + value.toString());
			Text distance = value.getProperties().get(new Text("distance"));
			if (i == 0) {
				Assert.assertEquals("0", key.toString());
				Assert.assertEquals("1.1313708498984762", distance.toString());
			} else if (i == 7) {
				Assert.assertEquals("1", key.toString());
				Assert.assertEquals("0.7071067811865476", distance.toString());
			}
			++i;
		}
		reader.close();
		Assert.assertEquals(9, i);
	}

	@Test
	public void runSquaredEuclideanDistanceMeasureKMeans() throws Exception {
		String testName = "runSquaredEuclideanDistanceMeasureKMeans";
		List<Vector> points = getPoints(POINTS);
		List<Vector> initialPoints = getInitialPoints(points,
				NUMBER_OF_CLUSTERS);
		String hdfsPointsFilePath = deleteAndGetHdfsPointsFilePath(testName);
		String hdfsClustersDirectoryPath = removeAndGetHdfsClustersDirectoryPath(testName);
		String hdfsOutputDirectoryPath = removeAndGetHdfsOutputDirectoryPath(testName);
		double convergenceDelta = 0.001;
		int maxIterations = 10;
		boolean runClustering = true;
		double clusterClassificationThreshold = 0;
		boolean runSequential = false;
		String pointsSeqenceFilePath = sequenceFilesAssistant
				.writePointsToFile(points, hdfsPointsFilePath);
		String resultFilePath = clusterAssistant
				.runSquaredEuclideanDistanceMeasureKMeans(initialPoints,
						pointsSeqenceFilePath, hdfsClustersDirectoryPath,
						hdfsOutputDirectoryPath, convergenceDelta,
						maxIterations, runClustering,
						clusterClassificationThreshold, runSequential);
		SequenceFile.Reader reader = hdfsAssistant.getReader(resultFilePath);
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
		int i = 0;
		System.out.println(testName + ":");
		while (reader.next(key, value)) {
			System.out.println(key.toString() + "/" + value.toString());
			Text distance = value.getProperties().get(new Text("distance"));
			if (i == 0) {
				Assert.assertEquals("0", key.toString());
				Assert.assertEquals("1.2800000000000002", distance.toString());
			} else if (i == 7) {
				Assert.assertEquals("1", key.toString());
				Assert.assertEquals("0.5", distance.toString());
			}
			++i;
		}
		reader.close();
		Assert.assertEquals(9, i);
	}

	@Test
	public void runManhattanDistanceMeasureKMeans() throws Exception {
		String testName = "runManhattanDistanceMeasureKMeans";
		List<Vector> points = getPoints(POINTS);
		List<Vector> initialPoints = getInitialPoints(points,
				NUMBER_OF_CLUSTERS);
		String hdfsPointsFilePath = deleteAndGetHdfsPointsFilePath(testName);
		String hdfsClustersDirectoryPath = removeAndGetHdfsClustersDirectoryPath(testName);
		String hdfsOutputDirectoryPath = removeAndGetHdfsOutputDirectoryPath(testName);
		double convergenceDelta = 0.001;
		int maxIterations = 10;
		boolean runClustering = true;
		double clusterClassificationThreshold = 0;
		boolean runSequential = false;
		String pointsSeqenceFilePath = sequenceFilesAssistant
				.writePointsToFile(points, hdfsPointsFilePath);
		String resultFilePath = clusterAssistant
				.runManhattanDistanceMeasureKMeans(initialPoints,
						pointsSeqenceFilePath, hdfsClustersDirectoryPath,
						hdfsOutputDirectoryPath, convergenceDelta,
						maxIterations, runClustering,
						clusterClassificationThreshold, runSequential);
		SequenceFile.Reader reader = hdfsAssistant.getReader(resultFilePath);
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
		int i = 0;
		System.out.println(testName + ":");
		while (reader.next(key, value)) {
			System.out.println(key.toString() + "/" + value.toString());
			Text distance = value.getProperties().get(new Text("distance"));
			if (i == 0) {
				Assert.assertEquals("0", key.toString());
				Assert.assertEquals("1.6", distance.toString());
			} else if (i == 7) {
				Assert.assertEquals("1", key.toString());
				Assert.assertEquals("1.0", distance.toString());
			}
			++i;
		}
		reader.close();
		Assert.assertEquals(9, i);
	}

	@Test
	public void runCosineDistanceMeasureKMeans() throws Exception {
		String testName = "runCosineDistanceMeasureKMeans";
		List<Vector> points = getPoints(POINTS);
		List<Vector> initialPoints = getInitialPoints(points,
				NUMBER_OF_CLUSTERS);
		String hdfsPointsFilePath = deleteAndGetHdfsPointsFilePath(testName);
		String hdfsClustersDirectoryPath = removeAndGetHdfsClustersDirectoryPath(testName);
		String hdfsOutputDirectoryPath = removeAndGetHdfsOutputDirectoryPath(testName);
		double convergenceDelta = 0.001;
		int maxIterations = 10;
		boolean runClustering = true;
		double clusterClassificationThreshold = 0;
		boolean runSequential = false;
		String pointsSeqenceFilePath = sequenceFilesAssistant
				.writePointsToFile(points, hdfsPointsFilePath);
		String resultFilePath = clusterAssistant
				.runCosineDistanceMeasureKMeans(initialPoints,
						pointsSeqenceFilePath, hdfsClustersDirectoryPath,
						hdfsOutputDirectoryPath, convergenceDelta,
						maxIterations, runClustering,
						clusterClassificationThreshold, runSequential);
		SequenceFile.Reader reader = hdfsAssistant.getReader(resultFilePath);
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
		int i = 0;
		System.out.println(testName + ":");
		while (reader.next(key, value)) {
			System.out.println(key.toString() + "/" + value.toString());
			Text distance = value.getProperties().get(new Text("distance"));
			if (i == 0) {
				Assert.assertEquals("0", key.toString());
				Assert.assertEquals("7.25715738101318E-5", distance.toString());
			} else if (i == 1) {
				Assert.assertEquals("1", key.toString());
				Assert.assertEquals("2.220446049250313E-16",
						distance.toString());
			}
			++i;
		}
		reader.close();
		Assert.assertEquals(9, i);
	}

	@Test
	public void runTanimotoDistanceMeasureKMeans() throws Exception {
		String testName = "runTanimotoDistanceMeasureKMeans";
		List<Vector> points = getPoints(POINTS);
		List<Vector> initialPoints = getInitialPoints(points,
				NUMBER_OF_CLUSTERS);
		String hdfsPointsFilePath = deleteAndGetHdfsPointsFilePath(testName);
		String hdfsClustersDirectoryPath = removeAndGetHdfsClustersDirectoryPath(testName);
		String hdfsOutputDirectoryPath = removeAndGetHdfsOutputDirectoryPath(testName);
		double convergenceDelta = 0.001;
		int maxIterations = 10;
		boolean runClustering = true;
		double clusterClassificationThreshold = 0;
		boolean runSequential = false;
		String pointsSeqenceFilePath = sequenceFilesAssistant
				.writePointsToFile(points, hdfsPointsFilePath);
		String resultFilePath = clusterAssistant
				.runTanimotoDistanceMeasureKMeans(initialPoints,
						pointsSeqenceFilePath, hdfsClustersDirectoryPath,
						hdfsOutputDirectoryPath, convergenceDelta,
						maxIterations, runClustering,
						clusterClassificationThreshold, runSequential);
		SequenceFile.Reader reader = hdfsAssistant.getReader(resultFilePath);
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
		int i = 0;
		System.out.println(testName + ":");
		while (reader.next(key, value)) {
			System.out.println(key.toString() + "/" + value.toString());
			Text distance = value.getProperties().get(new Text("distance"));
			if (i == 0) {
				Assert.assertEquals("0", key.toString());
				Assert.assertEquals("0.2622950819672132", distance.toString());
			} else if (i == 8) {
				Assert.assertEquals("1", key.toString());
				Assert.assertEquals("0.0032573289902280145",
						distance.toString());
			}
			++i;
		}
		reader.close();
		Assert.assertEquals(9, i);
	}

	private List<Vector> getPoints(double[][] points) {
		List<Vector> vectors = new ArrayList<Vector>();
		for (int i = 0; i < points.length; i++) {
			double[] fr = points[i];
			Vector vec = new RandomAccessSparseVector(fr.length);
			vec.assign(fr);
			vectors.add(vec);
		}
		return vectors;
	}

	private List<Vector> getInitialPoints(List<Vector> points,
			int numberOfClusters) {
		List<Vector> vectors = new ArrayList<Vector>();
		for (int i = 0; i < numberOfClusters; i++) {
			vectors.add(points.get(i));
		}
		return vectors;
	}

	private void init() throws IOException {
		// Create hdfs directory
		String userName = TestngSuitSetting.getUserName();
		clusterAssistantDirectoryPath = "/user/" + userName
				+ "/test/mahout/cluster_assistant";
		if (hdfsAssistant.exists(clusterAssistantDirectoryPath) == false) {
			hdfsAssistant.mkdir(clusterAssistantDirectoryPath);
		}
	}

	private String deleteAndGetHdfsPointsFilePath(String testName)
			throws IOException {
		String hdfsPointsFilePath = clusterAssistantDirectoryPath + "/"
				+ testName + "/points";
		if (hdfsAssistant.exists(hdfsPointsFilePath)) {
			hdfsAssistant.delete(hdfsPointsFilePath);
		}
		return hdfsPointsFilePath;
	}

	private String removeAndGetHdfsClustersDirectoryPath(String testName)
			throws IOException {
		String hdfsClustersDirectoryPath = clusterAssistantDirectoryPath + "/"
				+ testName + "/clusters";
		if (hdfsAssistant.exists(hdfsClustersDirectoryPath)) {
			hdfsAssistant.removeDirectory(hdfsClustersDirectoryPath);
		}
		hdfsAssistant.mkdir(hdfsClustersDirectoryPath);
		return hdfsClustersDirectoryPath;
	}

	private String removeAndGetHdfsOutputDirectoryPath(String testName)
			throws IOException {
		String hdfsOutputDirectoryPath = clusterAssistantDirectoryPath + "/"
				+ testName + "/output";
		if (hdfsAssistant.exists(hdfsOutputDirectoryPath)) {
			hdfsAssistant.removeDirectory(hdfsOutputDirectoryPath);
		}
		hdfsAssistant.mkdir(hdfsOutputDirectoryPath);
		return hdfsOutputDirectoryPath;
	}
}
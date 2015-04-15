package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;
import idv.hsiehpinghan.mahoutassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.clustering.kmeans.Kluster;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClusterAssistantTest {
	private String hdfsPointsFilePath;
	private String hdfsClustersDirectoryPath;
	private String hdfsOutputDirectoryPath;
	private final double[][] POINTS = { { 1, 1 }, { 2, 1 }, { 1, 2 }, { 2, 2 },
			{ 3, 3 }, { 8, 8 }, { 9, 8 }, { 8, 9 }, { 9, 9 } };
	private final int numberOfClusters = 2;
	private HdfsAssistant hdfsAssistant;
	private ClusterAssistant clusterAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		clusterAssistant = applicationContext.getBean(ClusterAssistant.class);
		setTestData();
	}

	@Test
	public void runKMeans() throws Exception {
		List<Vector> points = getPoints(POINTS);
		String hdfsPointsFilePath = writePointsToFile(points);
		String hdfsClustersDirPath = writeClusterInitialCenters(points);
		double convergenceDelta = 0.001;
		int maxIterations = 10;
		boolean runClustering = true;
		double clusterClassificationThreshold = 0;
		boolean runSequential = false;
		String resultFilePath = clusterAssistant.runKMeans(hdfsPointsFilePath,
				hdfsClustersDirPath, hdfsOutputDirectoryPath, convergenceDelta,
				maxIterations, runClustering, clusterClassificationThreshold,
				runSequential);
		SequenceFile.Reader reader = hdfsAssistant.getReader(resultFilePath);
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
		int i = 0;
		while (reader.next(key, value)) {
			System.err.println(key.toString() + "/" + value.toString());
			Text distance = value.getProperties().get(new Text("distance"));
			if(i == 0) {
				Assert.assertEquals("0", key.toString());
				Assert.assertEquals("1.1313708498984762", distance.toString());
			} else if(i == 7) {
				Assert.assertEquals("1", key.toString());
				Assert.assertEquals("0.7071067811865476", distance.toString());
			}
			++i;
		}
		reader.close();
		Assert.assertEquals(9, i);
	}

	private String writeClusterInitialCenters(List<Vector> points)
			throws IOException {
		String hdfsFilePath = hdfsClustersDirectoryPath + "/part-00000";
		SequenceFile.Writer writer = hdfsAssistant.getWriter(hdfsFilePath,
				Text.class, Kluster.class);
		for (int i = 0; i < numberOfClusters; i++) {
			final Vector vec = points.get(i);
			final Kluster cluster = new Kluster(vec, i,
					new EuclideanDistanceMeasure());
			writer.append(new Text(cluster.getIdentifier()), cluster);
		}
		writer.close();
		return hdfsClustersDirectoryPath;
	}

	private String writePointsToFile(List<Vector> points) throws IOException {
		SequenceFile.Writer writer = hdfsAssistant.getWriter(
				hdfsPointsFilePath, LongWritable.class, VectorWritable.class);
		VectorWritable vec = new VectorWritable();
		int i = 0;
		for (Vector point : points) {
			vec.set(point);
			writer.append(new LongWritable(++i), vec);
		}
		writer.close();
		return hdfsPointsFilePath;
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

	private void setTestData() throws IOException {
		// Create hdfs directory
		String userName = TestngSuitSetting.getUserName();
		String clusterAssistantDirectoryPath = "/user/" + userName
				+ "/test/mahout/cluster_assistant";
		if (hdfsAssistant.exists(clusterAssistantDirectoryPath) == false) {
			hdfsAssistant.mkdir(clusterAssistantDirectoryPath);
		}
		// delete points file
		hdfsPointsFilePath = clusterAssistantDirectoryPath + "/points";
		if (hdfsAssistant.exists(hdfsPointsFilePath)) {
			hdfsAssistant.delete(hdfsPointsFilePath);
		}
		// delete clusters directory
		hdfsClustersDirectoryPath = clusterAssistantDirectoryPath + "/clusters";
		if (hdfsAssistant.exists(hdfsClustersDirectoryPath)) {
			hdfsAssistant.removeDirectory(hdfsClustersDirectoryPath);
		}
		hdfsAssistant.mkdir(hdfsClustersDirectoryPath);
		// delete output file
		hdfsOutputDirectoryPath = clusterAssistantDirectoryPath + "/output";
		if (hdfsAssistant.exists(hdfsOutputDirectoryPath)) {
			hdfsAssistant.removeDirectory(hdfsOutputDirectoryPath);
		}
		hdfsAssistant.mkdir(hdfsOutputDirectoryPath);
	}
}
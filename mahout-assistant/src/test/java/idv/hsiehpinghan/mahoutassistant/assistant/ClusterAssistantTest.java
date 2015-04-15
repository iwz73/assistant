package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;
import idv.hsiehpinghan.mahoutassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClusterAssistantTest {
	private String clusterAssistantFilePath;
	private final double[][] points = { { 1, 1 }, { 2, 1 }, { 1, 2 }, { 2, 2 },
			{ 3, 3 }, { 8, 8 }, { 9, 8 }, { 8, 9 }, { 9, 9 } };
	private int k = 2;

	private HdfsAssistant hdfsAssistant;
	private ClusterAssistant clusterAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		setTestData(applicationContext);
		hdfsAssistant = applicationContext.getBean(HdfsAssistant.class);
		clusterAssistant = applicationContext.getBean(ClusterAssistant.class);
	}

	@Test
	public void cluster() {
		List<Vector> vectors = getPoints(points);

		System.err.println(vectors);
		
		clusterAssistant.cluster();
	}
	
	private List<Vector> getPoints(double[][] raw) {
		List<Vector> points = new ArrayList<Vector>();
		for (int i = 0; i < raw.length; i++) {
			double[] fr = raw[i];
			Vector vec = new RandomAccessSparseVector(fr.length);
			vec.assign(fr);
			points.add(vec);
		}
		return points;
	}
	
	private void setTestData(ApplicationContext applicationContext)
			throws IOException {
		String userName = TestngSuitSetting.getUserName();
		String clusterAssistantDirectoryPath = "/user/" + userName + "/test/mahout/cluster_assistant";
		if (hdfsAssistant.exists(clusterAssistantDirectoryPath) == false) {
			hdfsAssistant.mkdir(clusterAssistantDirectoryPath);
		}
		clusterAssistantFilePath = clusterAssistantDirectoryPath + "/clusterFile";
		if (hdfsAssistant.exists(clusterAssistantFilePath)) {
			hdfsAssistant.delete(clusterAssistantFilePath);
		}
	
	}
}

package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusterAssistant implements InitializingBean {
	private String hdfsPath;
	private Configuration conf;

	@Autowired
	private HdfsAssistant hdfsAssistant;

	@Override
	public void afterPropertiesSet() throws Exception {
		hdfsPath = hdfsAssistant.getHdfsPath();
		conf = hdfsAssistant.getHdfsConfiguration();
	}

	public String runKMeans(String hdfsPointsFilePath,
			String hdfsClustersDirPath, String hdfsOutputFilePath,
			double convergenceDelta, int maxIterations, boolean runClustering,
			double clusterClassificationThreshold, boolean runSequential)
			throws ClassNotFoundException, IOException, InterruptedException {
		Path points = new Path(hdfsPath + hdfsPointsFilePath);
		Path clusters = new Path(hdfsPath + hdfsClustersDirPath);
		Path output = new Path(hdfsPath + hdfsOutputFilePath);
		KMeansDriver.run(conf, points, clusters, output, convergenceDelta,
				maxIterations, runClustering, clusterClassificationThreshold,
				runSequential);
		return String.format("%s%s%s%s", hdfsOutputFilePath, "/",
				Cluster.CLUSTERED_POINTS_DIR, "/part-m-00000");
	}

}

package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.Kluster;
import org.apache.mahout.common.distance.CosineDistanceMeasure;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.common.distance.ManhattanDistanceMeasure;
import org.apache.mahout.common.distance.SquaredEuclideanDistanceMeasure;
import org.apache.mahout.common.distance.TanimotoDistanceMeasure;
import org.apache.mahout.math.Vector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusterAssistant implements InitializingBean {
	private String hdfsPath;
	private Configuration conf;

	@Autowired
	private HdfsAssistant hdfsAssistant;
	@Autowired
	private SequenceFilesAssistant sequenceFilesAssistant;

	@Override
	public void afterPropertiesSet() throws Exception {
		hdfsPath = hdfsAssistant.getHdfsPath();
		conf = hdfsAssistant.getHdfsConfiguration();
	}

	public String runEuclideanDistanceMeasureKMeans(List<Vector> initialPoints,
			String pointsSeqenceFilePath, String hdfsClustersDirectoryPath,
			String hdfsOutputDirectoryPath, double convergenceDelta,
			int maxIterations, boolean runClustering,
			double clusterClassificationThreshold, boolean runSequential)
			throws ClassNotFoundException, IOException, InterruptedException {
		DistanceMeasure distanceMeasure = new EuclideanDistanceMeasure();
		return runKMeans(initialPoints, pointsSeqenceFilePath,
				hdfsClustersDirectoryPath, hdfsOutputDirectoryPath,
				convergenceDelta, maxIterations, distanceMeasure,
				runClustering, clusterClassificationThreshold, runSequential);
	}

	public String runSquaredEuclideanDistanceMeasureKMeans(
			List<Vector> initialPoints, String pointsSeqenceFilePath,
			String hdfsClustersDirectoryPath, String hdfsOutputDirectoryPath,
			double convergenceDelta, int maxIterations, boolean runClustering,
			double clusterClassificationThreshold, boolean runSequential)
			throws ClassNotFoundException, IOException, InterruptedException {
		DistanceMeasure distanceMeasure = new SquaredEuclideanDistanceMeasure();
		return runKMeans(initialPoints, pointsSeqenceFilePath,
				hdfsClustersDirectoryPath, hdfsOutputDirectoryPath,
				convergenceDelta, maxIterations, distanceMeasure,
				runClustering, clusterClassificationThreshold, runSequential);
	}

	public String runManhattanDistanceMeasureKMeans(List<Vector> initialPoints,
			String pointsSeqenceFilePath, String hdfsClustersDirectoryPath,
			String hdfsOutputDirectoryPath, double convergenceDelta,
			int maxIterations, boolean runClustering,
			double clusterClassificationThreshold, boolean runSequential)
			throws ClassNotFoundException, IOException, InterruptedException {
		DistanceMeasure distanceMeasure = new ManhattanDistanceMeasure();
		return runKMeans(initialPoints, pointsSeqenceFilePath,
				hdfsClustersDirectoryPath, hdfsOutputDirectoryPath,
				convergenceDelta, maxIterations, distanceMeasure,
				runClustering, clusterClassificationThreshold, runSequential);
	}

	public String runCosineDistanceMeasureKMeans(List<Vector> initialPoints,
			String pointsSeqenceFilePath, String hdfsClustersDirectoryPath,
			String hdfsOutputDirectoryPath, double convergenceDelta,
			int maxIterations, boolean runClustering,
			double clusterClassificationThreshold, boolean runSequential)
			throws ClassNotFoundException, IOException, InterruptedException {
		DistanceMeasure distanceMeasure = new CosineDistanceMeasure();
		return runKMeans(initialPoints, pointsSeqenceFilePath,
				hdfsClustersDirectoryPath, hdfsOutputDirectoryPath,
				convergenceDelta, maxIterations, distanceMeasure,
				runClustering, clusterClassificationThreshold, runSequential);
	}

	public String runTanimotoDistanceMeasureKMeans(List<Vector> initialPoints,
			String pointsSeqenceFilePath, String hdfsClustersDirectoryPath,
			String hdfsOutputDirectoryPath, double convergenceDelta,
			int maxIterations, boolean runClustering,
			double clusterClassificationThreshold, boolean runSequential)
			throws ClassNotFoundException, IOException, InterruptedException {
		DistanceMeasure distanceMeasure = new TanimotoDistanceMeasure();
		return runKMeans(initialPoints, pointsSeqenceFilePath,
				hdfsClustersDirectoryPath, hdfsOutputDirectoryPath,
				convergenceDelta, maxIterations, distanceMeasure,
				runClustering, clusterClassificationThreshold, runSequential);
	}

	private String runKMeans(List<Vector> initialPoints,
			String pointsSeqenceFilePath, String hdfsClustersDirectoryPath,
			String hdfsOutputDirectoryPath, double convergenceDelta,
			int maxIterations, DistanceMeasure distanceMeasure,
			boolean runClustering, double clusterClassificationThreshold,
			boolean runSequential) throws ClassNotFoundException, IOException,
			InterruptedException {
		String clustersDirPath = writeClusterInitialCenters(initialPoints,
				hdfsClustersDirectoryPath, distanceMeasure);
		KMeansDriver.run(conf, new Path(hdfsPath + pointsSeqenceFilePath),
				new Path(hdfsPath + clustersDirPath), new Path(hdfsPath
						+ hdfsOutputDirectoryPath), convergenceDelta,
				maxIterations, runClustering, clusterClassificationThreshold,
				runSequential);
		return String.format("%s%s%s%s", hdfsOutputDirectoryPath, "/",
				Cluster.CLUSTERED_POINTS_DIR, "/part-m-00000");
	}

	private String writeClusterInitialCenters(List<Vector> initialPoints,
			String hdfsClustersDirectoryPath, DistanceMeasure distanceMeasure)
			throws IOException {
		String hdfsFilePath = hdfsClustersDirectoryPath + "/part-00000";
		SequenceFile.Writer writer = hdfsAssistant.getWriter(hdfsFilePath,
				Text.class, Kluster.class);
		for (int i = 0, size = initialPoints.size(); i < size; i++) {
			final Vector vec = initialPoints.get(i);
			final Kluster cluster = new Kluster(vec, i, distanceMeasure);
			writer.append(new Text(cluster.getIdentifier()), cluster);
		}
		writer.close();
		return hdfsClustersDirectoryPath;
	}
}

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
import org.apache.mahout.vectorizer.SparseVectorsFromSequenceFiles;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VectorAssistant {
	// private String hdfsPath;
	// private Configuration conf;
	//
	// @Autowired
	// private HdfsAssistant hdfsAssistant;
	// @Autowired
	// private SequenceFilesAssistant sequenceFilesAssistant;
	//
	// @Override
	// public void afterPropertiesSet() throws Exception {
	// hdfsPath = hdfsAssistant.getHdfsPath();
	// conf = hdfsAssistant.getHdfsConfiguration();
	// }

	public void transformSequenceFileToSparseVectors(
			String hdfsSequenceFileDirecotory, String hdfsOutputDirectory)
			throws Exception {
		String[] args = { "-i", hdfsSequenceFileDirecotory, "-o",
				hdfsOutputDirectory, "-a",
				"org.apache.lucene.analysis.WhitespaceAnalyzer", "-chunk",
				"200", "-wt", "tfidf", "-s", "5", "-md", "3", "-x", "90",
				"-ng", "2", "-ml", "50", "-seq" };
		SparseVectorsFromSequenceFiles.main(args);
	}
}

package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceFilesAssistant {
	@Autowired
	private HdfsAssistant hdfsAssistant;

	public String writePointsToFile(List<Vector> points,
			String hdfsPointsFilePath) throws IOException {
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

}

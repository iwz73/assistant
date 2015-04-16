package idv.hsiehpinghan.mahoutassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.assistant.HdfsAssistant;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceFileAssistant {
	@Autowired
	private HdfsAssistant hdfsAssistant;

	public String writePointsToSequenceFile(List<Vector> points,
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

	public String writeDirectoryToSequenceFile(String inputDirectoryPath,
			String hdfsOutputFilePath) throws Exception {
		SequenceFile.Writer writer = hdfsAssistant.getWriter(
				hdfsOutputFilePath, Text.class, BytesWritable.class);
		File docDirectory = new File(inputDirectoryPath);
		File[] files = docDirectory.listFiles();
		for (File file : files) {
			RandomAccessFile raFile = new RandomAccessFile(file, "r");
			byte[] content = new byte[(int) raFile.length()];
			raFile.readFully(content);
			writer.append(new Text(file.getName()), new BytesWritable(content));
			raFile.close();
		}
		writer.close();
		return hdfsOutputFilePath;
	}

	public SequenceFile.Reader readSequenceFile(String hdfsSequenceFilePath)
			throws IOException {
		return hdfsAssistant.getReader(hdfsSequenceFilePath);
	}
}

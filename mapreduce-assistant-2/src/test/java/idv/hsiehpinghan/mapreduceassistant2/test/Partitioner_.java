package idv.hsiehpinghan.mapreduceassistant2.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.springframework.stereotype.Component;

@Component
public class Partitioner_ {

	public boolean getMax(Configuration conf, Path inputPath, Path outputPath) throws Exception {
		Job job = Job.getInstance(conf, "partitioner_");
		job.setJarByClass(Partitioner_.class);
		job.setMapperClass(MapClass.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setPartitionerClass(CaderPartitioner.class);
		job.setReducerClass(ReduceClass.class);
		job.setNumReduceTasks(3);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		return job.waitForCompletion(true);
	}

	public static class MapClass extends Mapper<LongWritable, Text, Text, Text> {
		public void map(LongWritable key, Text value, Context context) {
			try {
				String[] str = value.toString().split("\t", -3);
				String gender = str[3];
				context.write(new Text(gender), new Text(value));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static class ReduceClass extends Reducer<Text, Text, Text, IntWritable> {
		public int max = -1;

		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			max = -1;

			for (Text val : values) {
				String[] str = val.toString().split("\t", -3);
				if (Integer.parseInt(str[4]) > max)
					max = Integer.parseInt(str[4]);
			}

			context.write(new Text(key), new IntWritable(max));
		}
	}

	public static class CaderPartitioner extends Partitioner<Text, Text> {
		@Override
		public int getPartition(Text key, Text value, int numReduceTasks) {
			String[] str = value.toString().split("\t");
			int age = Integer.parseInt(str[2]);

			if (numReduceTasks == 0) {
				return 0;
			}

			if (age <= 20) {
				return 0;
			} else if (age > 20 && age <= 30) {
				return 1 % numReduceTasks;
			} else {
				return 2 % numReduceTasks;
			}
		}
	}

}
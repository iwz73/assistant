package idv.hsiehpinghan.mapreduceassistant2.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.stereotype.Component;

@Component
public class InputFormatOutputFormat {

	public boolean count(Configuration conf, Path inputPath, Path outputPath) throws Exception {
		Job job = Job.getInstance(conf, "basic");
		job.setJarByClass(InputFormatOutputFormat.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
//		FileInputFormat.addInputPath(job, inputPath);
		job.setInputFormatClass(FileInputFormat_.class);
		FileOutputFormat.setOutputPath(job, outputPath);
		return job.waitForCompletion(true);
	}

	public static class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		private Text word = new Text();
		private final static IntWritable one = new IntWritable(1);

		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static class FileInputFormat_ extends InputFormat<LongWritable, Text> {

		@Override
		public List<InputSplit> getSplits(JobContext context) throws IOException, InterruptedException {
			List<InputSplit> result = new ArrayList<InputSplit>();
			InputSplit inputSplit = new InputSplit() {
				
				@Override
				public String[] getLocations() throws IOException, InterruptedException {
					// TODO Auto-generated method stub
					return new String[]{"thank"};
				}
				
				@Override
				public long getLength() throws IOException, InterruptedException {
					// TODO Auto-generated method stub
					return 45;
				}
			};
	
			result.add(inputSplit);
			return result;
		}

		@Override
		public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}


	}
}
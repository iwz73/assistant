package idv.hsiehpinghan.mapreduceassistant2.job;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.avro.util.Utf8;
import org.apache.gora.mapreduce.GoraInputFormat;
import org.apache.gora.mapreduce.GoraMapper;
import org.apache.gora.mapreduce.GoraOutputFormat;
import org.apache.gora.mapreduce.GoraReducer;
import org.apache.gora.query.Query;
import org.apache.gora.store.DataStore;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.nutch.crawl.GeneratorJob;
import org.apache.nutch.storage.Mark;
import org.apache.nutch.storage.StorageUtils;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.TableUtil;
import org.apache.nutch.util.URLUtil;
import org.springframework.stereotype.Component;

@Component
public class GoraInputFormatGoraOutputFormat {

	public boolean test(Configuration conf, Collection<WebPage.Field> fields) throws Exception {
		Job job = Job.getInstance(conf, "goraInputFormatGoraOutputFormat");
		DataStore<String, WebPage> dataStore = StorageUtils.createWebStore(job.getConfiguration(), String.class,
				WebPage.class);
		Query<String, WebPage> query = dataStore.newQuery();
		query.setFields(toStringArray(fields));
		job.setJarByClass(GoraInputFormatGoraOutputFormat.class);
		job.setMapperClass(GeneratorMapper.class);
		job.setMapOutputKeyClass(SelectorEntry.class);
		job.setMapOutputValueClass(WebPage.class);
		job.setReducerClass(GeneratorReducer.class);

		GoraInputFormat.setInput(job, query, dataStore, true);
	    
		GoraOutputFormat.setOutput(job, dataStore, true);


		
		// job.setJarByClass(GoraInputFormatGoraOutputFormat.class);
		// job.setMapperClass(TokenizerMapper.class);
		// job.setCombinerClass(IntSumReducer.class);
		// job.setReducerClass(IntSumReducer.class);
		// job.setOutputKeyClass(Text.class);
		// job.setOutputValueClass(IntWritable.class);
		// GoraInputFormat.setQuery(job, query);
		// job.setInputFormatClass(GoraInputFormat.class);
		//
		// TextOutputFormat.setOutputPath(job, outputPath);
		// job.setOutputFormatClass(TextOutputFormat.class);
		return job.waitForCompletion(true);
	}

	private String[] toStringArray(Collection<WebPage.Field> fields) {
		String[] arr = new String[fields.size()];
		Iterator<WebPage.Field> iter = fields.iterator();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = iter.next().getName();
		}
		return arr;
	}

	public static class GeneratorMapper extends GoraMapper<String, WebPage, SelectorEntry, WebPage> {
		private SelectorEntry entry = new SelectorEntry();

		@Override
		public void map(String reversedUrl, WebPage page, Context context) throws IOException, InterruptedException {
			String url = TableUtil.unreverseUrl(reversedUrl);
			float score = page.getScore();
			entry.set(url, score);
			context.write(entry, page);
		}

		@Override
		public void setup(Context context) {
			Configuration conf = context.getConfiguration();
		}
	}

	public static class GeneratorReducer extends GoraReducer<SelectorEntry, WebPage, String, WebPage> {

		private long limit;
		private long maxCount;
		protected static long count = 0;
		private boolean byDomain = false;
		private Map<String, Integer> hostCountMap = new HashMap<String, Integer>();
		private Utf8 batchId;

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration conf = context.getConfiguration();
			batchId = new Utf8(conf.get(GeneratorJob.BATCH_ID));
		}
		
		@Override
		protected void reduce(SelectorEntry key, Iterable<WebPage> values, Context context)
				throws IOException, InterruptedException {
			for (WebPage page : values) {
				Mark.GENERATE_MARK.putMark(page, batchId);
				page.setBatchId(batchId);
				try {
					context.write(TableUtil.reverseUrl(key.url), page);
				} catch (MalformedURLException e) {
					context.getCounter("Generator", "MALFORMED_URL").increment(1);
					continue;
				}
				context.getCounter("Generator", "GENERATE_MARK").increment(1);
				count++;
			}
		}

	}

	public static class SelectorEntry implements WritableComparable<SelectorEntry> {
		String url;
		float score;

		public SelectorEntry() {
		}

		public SelectorEntry(String url, float score) {
			this.url = url;
			this.score = score;
		}

		public void readFields(DataInput in) throws IOException {
			url = Text.readString(in);
			score = in.readFloat();
		}

		public void write(DataOutput out) throws IOException {
			Text.writeString(out, url);
			out.writeFloat(score);
		}

		public int compareTo(SelectorEntry se) {
			if (se.score > score)
				return 1;
			else if (se.score == score)
				return url.compareTo(se.url);
			return -1;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + url.hashCode();
			result = prime * result + Float.floatToIntBits(score);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			SelectorEntry other = (SelectorEntry) obj;
			if (!url.equals(other.url))
				return false;
			if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
				return false;
			return true;
		}

		public void set(String url, float score) {
			this.url = url;
			this.score = score;
		}
	}

}
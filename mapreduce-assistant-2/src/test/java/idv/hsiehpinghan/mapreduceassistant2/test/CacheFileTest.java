package idv.hsiehpinghan.mapreduceassistant2.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.mapreduceassistant2.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class CacheFileTest extends AbstractTestNGSpringContextTests {
	private Configuration conf;
	private Path inputPath;
	private Path outputPath;
	private Path patternPath;
	@Autowired
	private CacheFile cacheFile;

	@BeforeClass
	public void beforeClass() throws Exception {
		conf = new Configuration();
		inputPath = new Path(conf.get("fs.defaultFS") + "/tmp/cacheFile/file/");
		outputPath = new Path(conf.get("fs.defaultFS") + "/tmp/cacheFile/result/");
		patternPath = new Path(conf.get("fs.defaultFS") + "/tmp/cacheFile/pattern/");
		FileSystem fs = FileSystem.get(conf);
		deleteAndMkdirs(fs);
		Path[] srcs = generateSourcePaths();
		fs.copyFromLocalFile(false, true, srcs, inputPath);
		Path[] patterns = generatePatternPaths();
		fs.copyFromLocalFile(false, true, patterns, patternPath);
	}

	@Test
	public void count() throws Exception {
		Assert.assertTrue(cacheFile.count(conf, inputPath, outputPath,
				new Path(conf.get("fs.defaultFS") + "/tmp/cacheFile/pattern/patterns.txt")));
	}

	private boolean deleteAndMkdirs(FileSystem fs) throws IOException {
		fs.delete(inputPath, true);
		fs.delete(outputPath, true);
		fs.delete(patternPath, true);
		FsPermission permission = new FsPermission(FsAction.ALL, FsAction.NONE, FsAction.NONE);
		if (fs.mkdirs(inputPath, permission) == false) {
			return false;
		}
		if (fs.mkdirs(patternPath, permission) == false) {
			return false;
		}
		return true;
	}

	private Path[] generateSourcePaths() {
		Path wordCount_1 = new Path(
				"/home/hsiehpinghan/git/assistant/mapreduce-assistant-2/src/test/file/wordCount_1.txt");
		Path wordCount_2 = new Path(
				"/home/hsiehpinghan/git/assistant/mapreduce-assistant-2/src/test/file/wordCount_2.txt");
		return new Path[] { wordCount_1, wordCount_2 };
	}

	private Path[] generatePatternPaths() {
		Path patterns = new Path("/home/hsiehpinghan/git/assistant/mapreduce-assistant-2/src/test/file/patterns.txt");
		return new Path[] { patterns };
	}
}

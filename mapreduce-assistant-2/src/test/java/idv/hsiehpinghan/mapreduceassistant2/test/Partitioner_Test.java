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
import idv.hsiehpinghan.mapreduceassistant2.job.Partitioner_;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class Partitioner_Test extends AbstractTestNGSpringContextTests {
	private Path inputPath;
	private Path outputPath;
	@Autowired
	private Configuration conf;
	@Autowired
	private Partitioner_ partitioner;

	@BeforeClass
	public void beforeClass() throws Exception {
		inputPath = new Path(conf.get("fs.defaultFS") + "/tmp/partitioner/file/");
		outputPath = new Path(conf.get("fs.defaultFS") + "/tmp/partitioner/result/");
		FileSystem fs = FileSystem.get(conf);
		deleteAndMkdirs(fs);
		Path[] srcs = generateSourcePaths();
		fs.copyFromLocalFile(false, true, srcs, inputPath);
	}

	@Test
	public void count() throws Exception {
		Assert.assertTrue(partitioner.getMax(inputPath, outputPath));
	}

	private boolean deleteAndMkdirs(FileSystem fs) throws IOException {
		fs.delete(inputPath, true);
		fs.delete(outputPath, true);
		FsPermission permission = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL);
		if (fs.mkdirs(inputPath, permission) == false) {
			return false;
		}
		return true;
	}

	private Path[] generateSourcePaths() {
		Path partitioner = new Path(
				"/home/hsiehpinghan/git/assistant/mapreduce-assistant-2/src/test/file/partitioner.txt");
		return new Path[] { partitioner };
	}
}

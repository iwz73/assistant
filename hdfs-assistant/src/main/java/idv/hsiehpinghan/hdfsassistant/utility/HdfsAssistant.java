package idv.hsiehpinghan.hdfsassistant.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class HdfsAssistant {
	private Configuration conf = new Configuration();

	/**
	 * Write localFile to hdfs file.
	 * 
	 * @param hdfsFilePath
	 * @param localFile
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void writeHdfsFile(String hdfsFilePath, File localFile)
			throws IllegalArgumentException, IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsFilePath), conf);
		FileInputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(localFile);
			out = fs.create(new Path(hdfsFilePath));
			IOUtils.copyBytes(in, out, conf);
		} finally {
			IOUtils.closeStream(out);
			IOUtils.closeStream(in);
		}
	}

	/**
	 * Write local directory to hdfs directory.
	 * 
	 * @param hdfsDirectoryPath
	 * @param localDirectory
	 * @throws IOException
	 */
	public void writeHdfsDirectory(String hdfsDirectoryPath, File localDirectory)
			throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsDirectoryPath), conf);
		fs.mkdirs(new Path(hdfsDirectoryPath));
		File[] files = localDirectory.listFiles();
		for (File f : files) {
			writeHdfsFile(hdfsDirectoryPath + "/" + f.getName(), f);
		}
	}

	/**
	 * Copy hdfs file to outputStream.
	 * 
	 * @param hdfsFilePath
	 * @param outputStream
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void copyHdfsFile(String hdfsFilePath, OutputStream outputStream)
			throws IllegalArgumentException, IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsFilePath), conf);
		InputStream in = null;
		try {
			in = fs.open(new Path(hdfsFilePath));
			IOUtils.copyBytes(in, outputStream, conf);
		} finally {
			IOUtils.closeStream(in);
		}
	}

	/**
	 * Check if hdfsFilePath exists.
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean exists(String hdfsFilePath) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsFilePath), conf);
		return fs.exists(new Path(hdfsFilePath));
	}

	/**
	 * Recursively delete hdfsFilePath.
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean delete(String hdfsFilePath) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsFilePath), conf);
		return fs.delete(new Path(hdfsFilePath), true);
	}

	/**
	 * Get hdfsPath fileStatuses.
	 * 
	 * @param hdfsPath
	 * @return
	 * @throws IOException
	 */
	public FileStatus[] getFileStatuses(String hdfsPath) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		return fs.listStatus(new Path(hdfsPath));
	}
}

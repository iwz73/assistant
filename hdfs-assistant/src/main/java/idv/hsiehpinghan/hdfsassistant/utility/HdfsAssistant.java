package idv.hsiehpinghan.hdfsassistant.utility;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Component;

@Component
public class HdfsAssistant {
	private Configuration conf = new Configuration();

	/**
	 * Copy local file to hdfs.
	 * 
	 * @param localFile
	 * @param hdfsFilePath
	 * @throws IOException
	 */
	public void copyFromLocal(File localFile, String hdfsFilePath)
			throws IOException {
		copyFromLocal(localFile.getAbsolutePath(), hdfsFilePath);
	}

	/**
	 * Copy local file to hdfs.
	 * 
	 * @param localFilePath
	 * @param hdfsFilePath
	 * @throws IOException
	 */
	public void copyFromLocal(String localFilePath, String hdfsFilePath)
			throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsFilePath), conf);
		Path src = new Path(localFilePath);
		Path dst = new Path(hdfsFilePath);
		fs.copyFromLocalFile(src, dst);
	}

	/**
	 * Copy hdfs file to local.
	 * 
	 * @param hdfsFilePath
	 * @param localFile
	 * @throws IOException
	 */
	public void copyToLocal(String hdfsFilePath, File localFile)
			throws IOException {
		copyToLocal(hdfsFilePath, localFile.getAbsolutePath());
	}

	/**
	 * Copy hdfs file to local.
	 * 
	 * @param hdfsFilePath
	 * @param localFilePath
	 * @throws IOException
	 */
	public void copyToLocal(String hdfsFilePath, String localFilePath)
			throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsFilePath), conf);
		Path src = new Path(hdfsFilePath);
		Path dst = new Path(localFilePath);
		fs.copyToLocalFile(src, dst);
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

}

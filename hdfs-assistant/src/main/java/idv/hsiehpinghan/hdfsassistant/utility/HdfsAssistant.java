package idv.hsiehpinghan.hdfsassistant.utility;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HdfsAssistant implements InitializingBean {
	private Configuration conf = new Configuration();
	private String hdfsPath;

	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		String pStr = "hdfs-assistant.hdfs_path";
		hdfsPath = environment.getProperty(pStr);
		if (hdfsPath == null) {
			throw new RuntimeException(pStr + " not set !!!");
		}
	}

	/**
	 * Copy local file to hdfs.
	 * 
	 * @param localFile
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public String copyFromLocal(File localFile, String hdfsFilePath)
			throws IOException {
		return copyFromLocal(localFile.getAbsolutePath(), hdfsFilePath);
	}

	/**
	 * Copy local file to hdfs.
	 * 
	 * @param localFilePath
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public String copyFromLocal(String localFilePath, String hdfsFilePath)
			throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		Path src = new Path(localFilePath);
		String path = hdfsPath + hdfsFilePath;
		Path dst = new Path(path);
		fs.copyFromLocalFile(src, dst);
		return path;
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
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		Path src = new Path(hdfsPath + hdfsFilePath);
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
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		return fs.exists(new Path(hdfsPath + hdfsFilePath));
	}

	/**
	 * Recursively delete hdfsFilePath.
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean delete(String hdfsFilePath) throws IOException {
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		return fs.delete(new Path(hdfsPath + hdfsFilePath), true);
	}

}

package idv.hsiehpinghan.hdfsassistant.assistant;

import idv.hsiehpinghan.hdfsassistant.property.HdfsAssistantProperty;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeys;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HdfsAssistant implements InitializingBean {
	private Configuration conf = new Configuration();
	private String hdfsPath;

	@Autowired
	private HdfsAssistantProperty hdfsAssistantProperty;

	@Override
	public void afterPropertiesSet() throws Exception {
		hdfsPath = hdfsAssistantProperty.getHdfsPath();
		conf.set(CommonConfigurationKeys.FS_DEFAULT_NAME_KEY, hdfsPath);
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
		FileSystem fs = FileSystem.get(conf);
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
		FileSystem fs = FileSystem.get(conf);
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
		FileSystem fs = FileSystem.get(conf);
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
		FileSystem fs = FileSystem.get(conf);
		return fs.delete(new Path(hdfsPath + hdfsFilePath), true);
	}

	/**
	 * Recursively delete a directory.
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean removeDirectory(String hdfsFilePath) throws IOException {
		return delete(hdfsFilePath);
	}

	/**
	 * Recursively make dir.
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public boolean mkdir(String hdfsFilePath) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		return fs.mkdirs(new Path(hdfsPath + hdfsFilePath));
	}

	/**
	 * Get sequence file writer.
	 * 
	 * @param hdfsFilePath
	 * @param keyClass
	 * @param valueClass
	 * @return
	 * @throws IOException
	 */
	public SequenceFile.Writer getWriter(String hdfsFilePath,
			Class<?> keyClass, Class<?> valueClass) throws IOException {
		SequenceFile.Writer.Option filePath = SequenceFile.Writer
				.file(new Path(hdfsFilePath));
		SequenceFile.Writer.Option keyClz = SequenceFile.Writer
				.keyClass(keyClass);
		SequenceFile.Writer.Option valueClz = SequenceFile.Writer
				.valueClass(valueClass);
		return SequenceFile.createWriter(conf, filePath, keyClz, valueClz);
	}

	/**
	 * Get sequence file reader.
	 * 
	 * @param hdfsFilePath
	 * @return
	 * @throws IOException
	 */
	public SequenceFile.Reader getReader(String hdfsFilePath)
			throws IOException {
		SequenceFile.Reader.Option filePath = SequenceFile.Reader
				.file(new Path(hdfsFilePath));
		return new SequenceFile.Reader(conf, filePath);
	}

	public Configuration getHdfsConfiguration() {
		return conf;
	}

	public String getHdfsPath() {
		return hdfsPath;
	}
}

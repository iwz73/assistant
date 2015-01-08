package idv.hsiehpinghan.hdfsassistant.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HdfsAssistantProperty implements InitializingBean {
	private String hdfsPath;

	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		String pHdfsPath = "hdfs-assistant.hdfs_path";
		hdfsPath = environment.getProperty(pHdfsPath);
		if (hdfsPath == null) {
			throw new RuntimeException(pHdfsPath + " not set !!!");
		}
	}

	public String getHdfsPath() {
		return hdfsPath;
	}

}

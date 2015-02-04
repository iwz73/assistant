package idv.hsiehpinghan.hbaseassistant.configuration;

import idv.hsiehpinghan.hbaseassistant.extension.HbaseTemplateExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@PropertySource("classpath:/hbase_assistant.property")
@Configuration("hbaseAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.hbaseassistant" })
public class SpringConfiguration {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private org.apache.hadoop.conf.Configuration hbaseConfiguration;

	@Bean
	public HbaseTemplateExtension hbaseTemplate() throws IOException {
		HbaseTemplateExtension hbaseTemplateExtension = new HbaseTemplateExtension(
				hbaseConfiguration());
		return hbaseTemplateExtension;
	}

	@Bean
	public HBaseAdmin hBaseAdmin() throws Exception {
		return new HBaseAdmin(hbaseConfiguration());
	}

	private org.apache.hadoop.conf.Configuration hbaseConfiguration()
			throws IOException {
		if (hbaseConfiguration != null) {
			return hbaseConfiguration;
		}
		hbaseConfiguration = new org.apache.hadoop.conf.Configuration();
		addConfiguration(hbaseConfiguration);

		// Show properties info.
		Iterator<Map.Entry<String, String>> iter = hbaseConfiguration
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			logger.info("hbase-utility configuration :" + entry.getKey()
					+ " : " + entry.getValue());
		}
		return hbaseConfiguration;
	}

	private void addConfiguration(org.apache.hadoop.conf.Configuration config)
			throws IOException {
		String fileName = "hbase-site.xml";
		Resource resource = new ClassPathResource(fileName);
		File file = resource.getFile();
		if (file.exists() == false) {
			throw new RuntimeException(fileName + " not exists !!!");
		}
		config.addResource(new FileInputStream(file));
	}
}

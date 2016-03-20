package idv.hsiehpinghan.springdatahadoopassistant.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@Configuration("springDataHadoopAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springdatahadoopassistant" })
public class SpringConfiguration {
	private static Logger logger = LoggerFactory
			.getLogger(SpringConfiguration.class);

	@Bean
	public HbaseTemplate hbaseTemplate(
			@Qualifier("hbaseConfiguration") org.apache.hadoop.conf.Configuration configuration) {
		HbaseTemplate HbaseTemplate = new HbaseTemplate(configuration);
		return HbaseTemplate;
	}

	@Bean
	public org.apache.hadoop.conf.Configuration hbaseConfiguration()
			throws IOException {
		org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
		setConfiguration(configuration);
		// Show properties info.
		Iterator<Map.Entry<String, String>> iter = configuration.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			logger.debug("spring-data-hadoop-assistant configuration :"
					+ entry.getKey() + " : " + entry.getValue());
		}
		return configuration;
	}

	private void setConfiguration(org.apache.hadoop.conf.Configuration config)
			throws IOException {
		String fileName = "hbase-site.xml";
		Resource resource = new ClassPathResource(fileName);
		config.addResource(resource.getInputStream());
	}
}
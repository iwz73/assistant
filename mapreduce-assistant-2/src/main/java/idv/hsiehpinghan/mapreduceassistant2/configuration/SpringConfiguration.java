package idv.hsiehpinghan.mapreduceassistant2.configuration;

import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.NutchConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import idv.hsiehpinghan.nutch.storage.Seed;

@Configuration("mapreduceAssistant2SpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.mapreduceassistant2" })
public class SpringConfiguration {
	@Bean
	public DataStore<String, WebPage> webPageDataStore(org.apache.hadoop.conf.Configuration configuration)
			throws GoraException {
		return DataStoreFactory.getDataStore(String.class, WebPage.class, configuration);
	}

	@Bean
	public DataStore<String, Seed> seedDataStore(org.apache.hadoop.conf.Configuration configuration)
			throws GoraException {
		return DataStoreFactory.getDataStore(String.class, Seed.class, configuration);
	}

	@Bean
	public org.apache.hadoop.conf.Configuration configuration() {
		org.apache.hadoop.conf.Configuration conf = NutchConfiguration.create();
		conf.addResource("nutch-site.xml");
		return conf;
	}
}
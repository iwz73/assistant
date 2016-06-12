package idv.hsiehpinghan.mapreduceassistant2.configuration;

import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.NutchConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("mapreduceAssistant2SpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.mapreduceassistant2" })
public class SpringConfiguration {
	@Bean
	public DataStore<String, WebPage> webPageDataStore(org.apache.hadoop.conf.Configuration configuration)
			throws GoraException {
		return DataStoreFactory.getDataStore(String.class, WebPage.class, configuration);
	}

	@Bean
	public org.apache.hadoop.conf.Configuration configuration() {
		org.apache.hadoop.conf.Configuration conf = NutchConfiguration.create();
		conf.set("preferred.schema.name", "webpage");
		return conf;
	}
}
package idv.hsiehpinghan.goraassistant.configuration;

import idv.hsiehpinghan.goraassistant.entity.Gora;

import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.NutchConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("goraAssistantSpringConfigurationTest")
@ComponentScan(basePackages = { "idv.hsiehpinghan.goraassistant" })
public class SpringConfigurationTest {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public DataStore<Long, Gora> goraDataStore(
			org.apache.hadoop.conf.Configuration configuration)
			throws GoraException {
		return DataStoreFactory.getDataStore(Long.class, Gora.class,
				configuration);
	}

	@Bean
	public DataStore<String, WebPage> webPageDataStore(
			org.apache.hadoop.conf.Configuration configuration)
			throws GoraException {
		return DataStoreFactory.getDataStore(String.class, WebPage.class,
				configuration);
	}

	@Bean
	public org.apache.hadoop.conf.Configuration configuration() {
		return NutchConfiguration.create();
	}
}
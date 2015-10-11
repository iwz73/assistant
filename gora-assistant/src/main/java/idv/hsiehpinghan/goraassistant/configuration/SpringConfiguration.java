package idv.hsiehpinghan.goraassistant.configuration;

import idv.hsiehpinghan.goraassistant.entity.Gora;

import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("goraAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.goraassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public DataStore<Long, Gora> dataStore() throws GoraException {
		return DataStoreFactory.getDataStore(Long.class, Gora.class,
				new org.apache.hadoop.conf.Configuration());
	}
}
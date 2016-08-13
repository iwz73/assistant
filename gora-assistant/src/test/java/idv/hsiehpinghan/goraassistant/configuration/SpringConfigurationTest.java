package idv.hsiehpinghan.goraassistant.configuration;

import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.NutchConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import idv.hsiehpinghan.goraassistant.entity.Gora;

@Configuration("goraAssistantSpringConfigurationTest")
@ComponentScan(basePackages = { "idv.hsiehpinghan.goraassistant" })
public class SpringConfigurationTest {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

}
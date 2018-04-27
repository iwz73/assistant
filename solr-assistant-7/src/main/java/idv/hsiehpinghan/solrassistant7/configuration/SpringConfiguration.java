package idv.hsiehpinghan.solrassistant7.configuration;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/solr_assistant_7.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.solrassistant7" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Environment environment;

	@Bean
	public HttpSolrClient queryHttpSolrClient() {
		String solrUrl = environment.getRequiredProperty("solrUrl");
		HttpSolrClient httpSolrClient = (new HttpSolrClient.Builder(solrUrl)).build();
		return httpSolrClient;
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public HttpSolrClient updateHttpSolrClient() {
		String solrUrl = environment.getRequiredProperty("solrUrl");
		HttpSolrClient httpSolrClient = (new HttpSolrClient.Builder(solrUrl)).build();
		return httpSolrClient;
	}
}
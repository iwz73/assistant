package idv.hsiehpinghan.solrassistant.configuration;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/solr_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.solrassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Environment environment;

	@Bean
	public HttpSolrServer httpSolrServer() {
		String solrUrl = environment.getRequiredProperty("solrUrl");
		HttpSolrServer httpSolrServer = new HttpSolrServer(solrUrl);
		return httpSolrServer;
	}
}
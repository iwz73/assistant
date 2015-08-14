package idv.hsiehpinghan.springdatasolrassistant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrServerFactoryBean;

@Configuration
@ComponentScan(basePackages = { "idv.hsiehpinghan.springdatasolrassistant" })
@EnableSolrRepositories("idv.hsiehpinghan.springdatasolrassistant.repository")
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Bean
	public HttpSolrServerFactoryBean solrServerFactoryBean() {
		HttpSolrServerFactoryBean factory = new HttpSolrServerFactoryBean();
		factory.setUrl("http://localhost:8983/solr");
		return factory;
	}

	@Bean
	public SolrTemplate solrTemplate() throws Exception {
		return new SolrTemplate(solrServerFactoryBean().getObject());
	}

	// @Bean
	// public SolrServer solrServer() {
	// return new HttpSolrServer("http://localhost:8983/solr");
	// }
	//
	// @Bean
	// public SolrTemplate solrTemplate(SolrServer server) throws Exception {
	// return new SolrTemplate(server);
	// }
}
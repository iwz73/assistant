package idv.hsiehpinghan.neo4jassistant.configuration;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration("neo4jAssistantSpringConfiguration")
@PropertySource("classpath:/neo4j_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.neo4jassistant" })
public class SpringConfiguration {
	@Value("${uri}")
	private String uri;
	@Value("${username}")
	private String username;
	@Value("${password}")
	private String password;
	@Value("${packages}")
	private String[] packages;

	@Bean
	public Driver driver() {
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
		return driver;
	}

	@Bean
	public SessionFactory sessionFactory() {
		org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder().uri(uri)
				.credentials(username, password).build();
		SessionFactory sessionFactory = new SessionFactory(configuration, packages);
		return sessionFactory;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
package idv.hsiehpinghan.neo4jassistant.configuration;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
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

	@Bean
	public Driver driver() {
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
		return driver;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
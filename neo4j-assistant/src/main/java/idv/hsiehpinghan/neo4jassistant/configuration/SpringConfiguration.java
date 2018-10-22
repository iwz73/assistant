package idv.hsiehpinghan.neo4jassistant.configuration;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.session.event.EventListener;
import org.neo4j.ogm.session.event.EventListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import idv.hsiehpinghan.neo4jassistant.adapter.BasicEventListenerAdapter;
import idv.hsiehpinghan.neo4jassistant.listener.BasicEventListener;

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
	@Value("${autoIndex}")
	private String autoIndex;

	@Bean
	public Driver driver() {
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
		return driver;
	}

	@Bean
	public SessionFactory sessionFactory() {
		org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder().uri(uri)
				.credentials(username, password).autoIndex(autoIndex).build();
		SessionFactory sessionFactory = new SessionFactory(configuration, packages);
		EventListener eventListener = new BasicEventListener();
		sessionFactory.register(eventListener);
		EventListenerAdapter eventListenerAdapter = new BasicEventListenerAdapter();
		sessionFactory.register(eventListenerAdapter);	
		return sessionFactory;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
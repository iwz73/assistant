package idv.hsiehpinghan.mongodbassistant.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;

@Configuration("mongodbAssistantSpringConfiguration")
@PropertySource("classpath:/mongodb_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.mongodbassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Environment env;

	@Bean
	public MongoClient mongoClient() throws IOException {
		String host = env.getRequiredProperty("host");
		int port = Integer.valueOf(env.getRequiredProperty("port"));
		MongoClient mongoClient = new MongoClient(host, port);
		return mongoClient;
	}

}

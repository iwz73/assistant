package idv.hsiehpinghan.springdatamongodbassistant.configuration;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration("springDataMongodbAssistantSpringConfiguration")
@PropertySource("classpath:/spring_data_mongodb_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springdatamongodbassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Environment env;

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
		return mongoTemplate;
	}

	@Bean
	public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
		String databaseName = env.getRequiredProperty("mongodb.databaseName");
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, databaseName);
		return mongoDbFactory;
	}

	@Bean
	public MongoClient mongoClient() throws IOException {
		String host = env.getRequiredProperty("mongodb.host");
		int port = Integer.valueOf(env.getRequiredProperty("mongodb.port"));
		MongoClient mongoClient = new MongoClient(host, port);
		return mongoClient;
	}

}

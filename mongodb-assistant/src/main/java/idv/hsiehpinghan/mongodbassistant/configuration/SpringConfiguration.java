package idv.hsiehpinghan.mongodbassistant.configuration;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration("mongodbAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.mongodbassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public MongoClient mongoClient() throws IOException {
		MongoClient mongoClient = new MongoClient();
		mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongoClient;
	}

}

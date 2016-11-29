package idv.hsiehpinghan.springdatamongodbassistant.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration("springDataMongodbAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springdatamongodbassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public MongoClient mongoClient() throws IOException {
		MongoClient mongoClient = new MongoClient();
		mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongoClient;
	}

}

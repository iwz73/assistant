package idv.hsiehpinghan.mongodbassistant.assistant;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

@Component
public class MongodbAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private MongoClient mongoClient;

	public MongoOperations getMongoOperations(String databaseName)
			throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient,
				databaseName);
		mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		return mongoTemplate;
	}

}

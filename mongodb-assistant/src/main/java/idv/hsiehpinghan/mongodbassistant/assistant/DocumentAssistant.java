package idv.hsiehpinghan.mongodbassistant.assistant;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

@Component
public class DocumentAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private MongoClient mongoClient;

	public Document parse(String json) {
		return Document.parse(json);
	}

}

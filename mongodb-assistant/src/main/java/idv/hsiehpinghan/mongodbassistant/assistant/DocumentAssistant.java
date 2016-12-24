package idv.hsiehpinghan.mongodbassistant.assistant;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	public Document parse(String json) {
		return Document.parse(json);
	}

}

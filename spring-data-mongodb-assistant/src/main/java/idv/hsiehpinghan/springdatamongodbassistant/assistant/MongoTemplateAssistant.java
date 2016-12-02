package idv.hsiehpinghan.springdatamongodbassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springdatamongodbassistant.entity.BasicDocument;

@Component
public class MongoTemplateAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	private final Class<BasicDocument> ENTITY_CLASS = BasicDocument.class;
	@Autowired
	private MongoTemplate mongoTemplate;

	public void dropCollection() {
		mongoTemplate.dropCollection(ENTITY_CLASS);
	}

	public void insert(BasicDocument entity) {
		mongoTemplate.insert(entity);
	}

	public BasicDocument findOne(Query query) {
		return mongoTemplate.findOne(query, ENTITY_CLASS);
	}
}

package idv.hsiehpinghan.springbatchassistant.processor;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemProcessor;

import idv.hsiehpinghan.springbatchassistant.entity.MongodbDocument;

public class MongodbProcessor implements ItemProcessor<MongodbDocument, MongodbDocument> {

	@Override
	public MongodbDocument process(MongodbDocument item) throws Exception {
		ObjectId id = new ObjectId();
		Long longValue = item.getLongValue() + 100;
		String stringValue = item.getStringValue() + "_processed";
		item.setId(id);
		item.setLongValue(longValue);
		item.setStringValue(stringValue);
		return item;
	}

}
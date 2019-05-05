package idv.hsiehpinghan.springbatchassistant.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongodbDocument {
	@Id
	private ObjectId id;
	private Long longValue;
	private String stringValue;

	public MongodbDocument(ObjectId id, Long longValue, String stringValue) {
		super();
		this.id = id;
		this.longValue = longValue;
		this.stringValue = stringValue;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	@Override
	public String toString() {
		return "MongodbDocument [id=" + id + ", longValue=" + longValue + ", stringValue=" + stringValue + "]";
	}

}

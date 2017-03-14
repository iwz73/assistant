package idv.hsiehpinghan.hibernatesearchormassistant.bridge;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexableField;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdEmbeddableEntity;

public class EmbeddedIdTwoWayFieldBridge implements TwoWayFieldBridge {

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		EmbeddedIdEmbeddableEntity embeddedIdEmbeddableEntity = (EmbeddedIdEmbeddableEntity) value;
		Store store = luceneOptions.getStore();
		Float boost = luceneOptions.getBoost();
		Field field = null;
		field = new StringField(name + ".firstName", embeddedIdEmbeddableEntity.getFirstName(), store);
		field.setBoost(boost);
		document.add(field);
		field = new StringField(name + ".lastName", embeddedIdEmbeddableEntity.getLastName(), store);
		field.setBoost(boost);
		document.add(field);
	}

	@Override
	public Object get(String name, Document document) {
		EmbeddedIdEmbeddableEntity embeddedIdEmbeddableEntity = new EmbeddedIdEmbeddableEntity();
		IndexableField field = null;
		field = document.getField(name + ".firstName");
		embeddedIdEmbeddableEntity.setFirstName(field.stringValue());
		field = document.getField(name + ".lastName");
		embeddedIdEmbeddableEntity.setLastName(field.stringValue());
		return embeddedIdEmbeddableEntity;
	}

	@Override
	public String objectToString(Object object) {
		EmbeddedIdEmbeddableEntity embeddedIdEmbeddableEntity = (EmbeddedIdEmbeddableEntity) object;
		StringBuilder sb = new StringBuilder();
		sb.append(embeddedIdEmbeddableEntity);
		sb.append(embeddedIdEmbeddableEntity.getFirstName());
		sb.append("_");
		sb.append(embeddedIdEmbeddableEntity.getLastName());
		return sb.toString();
	}

}

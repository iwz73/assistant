package idv.hsiehpinghan.hibernatesearchormassistant.bridge;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexableField;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.BridgeEntity;

public class EmbeddedIdTwoWayFieldBridgeBridge implements TwoWayFieldBridge {

	@Override
	public void set(String name, Object object, Document document, LuceneOptions luceneOptions) {
		if ((object instanceof BridgeEntity.BridgeEntityId) == false) {
			throw new RuntimeException("object class(" + object.getClass() + ") is not instance of Double !!!");
		}
		BridgeEntity.BridgeEntityId bridgeEntityId = (BridgeEntity.BridgeEntityId) object;
		Store store = luceneOptions.getStore();
		Float boost = luceneOptions.getBoost();
		Field field = null;
		field = new StringField(name + ".firstName", bridgeEntityId.getFirstName(), store);
		field.setBoost(boost);
		document.add(field);
		field = new StringField(name + ".lastName", bridgeEntityId.getLastName(), store);
		field.setBoost(boost);
		document.add(field);
	}

	@Override
	public Object get(String name, Document document) {
		BridgeEntity.BridgeEntityId bridgeEntityId = new BridgeEntity.BridgeEntityId();
		IndexableField field = null;
		field = document.getField(name + ".firstName");
		bridgeEntityId.setFirstName(field.stringValue());
		field = document.getField(name + ".lastName");
		bridgeEntityId.setLastName(field.stringValue());
		return bridgeEntityId;
	}

	@Override
	public String objectToString(Object object) {
		BridgeEntity.BridgeEntityId bridgeEntityId = (BridgeEntity.BridgeEntityId) object;
		StringBuilder sb = new StringBuilder();
		sb.append(bridgeEntityId);
		sb.append(bridgeEntityId.getFirstName());
		sb.append("_");
		sb.append(bridgeEntityId.getLastName());
		return sb.toString();
	}

}

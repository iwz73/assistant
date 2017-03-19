package idv.hsiehpinghan.hibernatesearchormassistant.bridge;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

public class FieldBridgeBridge implements FieldBridge {

	@Override
	public void set(String name, Object object, Document document, LuceneOptions luceneOptions) {
		if ((object instanceof Map) == false) {
			throw new RuntimeException("object class(" + object.getClass() + ") is not instance of Map !!!");
		}
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = (Map<String, Integer>) object;
		Store store = luceneOptions.getStore();
		Float boost = luceneOptions.getBoost();
		for (Map.Entry<String, Integer> ent : map.entrySet()) {
			Field field = new StringField(name + "." + ent.getKey(), String.valueOf(ent.getValue()), store);
			field.setBoost(boost);
			document.add(field);
		}
	}

}

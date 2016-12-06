package idv.hsiehpinghan.mongodbassistant.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.model.DeleteManyModel;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;

public class MongodbUtility {
	public static Document generateCopiedDocument(Document document, String[] fields) {
		Document doc = new Document();
		for (int i = 0, size = fields.length; i < size; ++i) {
			String key = fields[i];
			Object value = document.get(key);
			doc.append(key, value);
		}
		return doc;
	}

	public static InsertOneModel<Document> generateInsertOneModel(Document insert) {
		InsertOneModel<Document> insertOneModel = new InsertOneModel<>(insert);
		return insertOneModel;
	}

	public static ReplaceOneModel<Document> generateReplaceOneModel(Document replacement, Bson filter) {
		ReplaceOneModel<Document> replaceOneModel = new ReplaceOneModel<>(filter, replacement);
		return replaceOneModel;
	}

	public static ReplaceOneModel<Document> generateReplaceOneModelById(Document replacement) {
		Object id = replacement.get("_id");
		Bson filter = Filters.eq("_id", id);
		return generateReplaceOneModel(replacement, filter);
	}

	public static UpdateOneModel<Document> generateSetUpdateOneModel(Document update, Bson filter) {
		Document setUpdate = new Document("$set", update);
		UpdateOneModel<Document> updateOneModel = new UpdateOneModel<>(filter, setUpdate);
		return updateOneModel;
	}

	public static UpdateOneModel<Document> generateSetUpdateOneModelById(Document update) {
		Object id = update.get("_id");
		Bson filter = Filters.eq("_id", id);
		UpdateOneModel<Document> updateOneModel = generateSetUpdateOneModel(update, filter);
		return updateOneModel;
	}

	public static UpdateManyModel<Document> generateSetUpdateManyModel(Document update, Bson filter) {
		Document setUpdate = new Document("$set", update);
		UpdateManyModel<Document> updateManyModel = new UpdateManyModel<>(filter, setUpdate);
		return updateManyModel;
	}

	public static UpdateManyModel<Document> generateSetUpdateManyModelById(Document update,
			Collection<Document> documents) {
		final int SIZE = documents.size();
		Document setUpdate = new Document("$set", update);
		List<Bson> fltr = new ArrayList<>(SIZE);
		for (Document document : documents) {
			Object id = document.get("_id");
			Bson filter = Filters.eq("_id", id);
			fltr.add(filter);
		}
		Bson filter = Filters.or(fltr);
		UpdateManyModel<Document> updateManyModel = new UpdateManyModel<>(filter, setUpdate);
		return updateManyModel;
	}

	public static DeleteOneModel<Document> generateDeleteOneModel(Document document, Bson filter) {
		DeleteOneModel<Document> deleteOneModel = new DeleteOneModel<>(filter);
		return deleteOneModel;
	}

	public static DeleteOneModel<Document> generateDeleteOneModelById(Document document) {
		Object id = document.get("_id");
		Bson filter = Filters.eq("_id", id);
		return generateDeleteOneModel(document, filter);
	}

	public static DeleteManyModel<Document> generateDeleteManyModel(Bson filter) {
		DeleteManyModel<Document> deleteManyModel = new DeleteManyModel<>(filter);
		return deleteManyModel;
	}

	public static DeleteManyModel<Document> generateDeleteManyModelById(Collection<Document> documents) {
		final int SIZE = documents.size();
		List<Bson> fltr = new ArrayList<>(SIZE);
		for (Document document : documents) {
			Object id = document.get("_id");
			Bson filter = Filters.eq("_id", id);
			fltr.add(filter);
		}
		Bson filter = Filters.or(fltr);
		DeleteManyModel<Document> deleteManyModel = new DeleteManyModel<>(filter);
		return deleteManyModel;
	}

	public static UpdateOneModel<Document> generateSetUpdateOneModelForUpsert(Bson filter, Document update,
			UpdateOptions updateOptions) {
		Document setUpdate = new Document("$set", update);
		if (updateOptions == null) {
			return new UpdateOneModel<>(filter, setUpdate);
		}
		return new UpdateOneModel<>(filter, setUpdate, updateOptions);
	}

	public static UpdateOneModel<Document> generateSetUpdateOneModelForUpsertById(Document update) {
		Object id = update.get("_id");
		Bson filter = Filters.eq("_id", id);
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		UpdateOneModel<Document> updateOneModel = generateSetUpdateOneModelForUpsert(filter, update, updateOptions);
		return updateOneModel;
	}

}

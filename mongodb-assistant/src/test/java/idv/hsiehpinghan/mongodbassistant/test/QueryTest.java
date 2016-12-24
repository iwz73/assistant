package idv.hsiehpinghan.mongodbassistant.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.BulkWriteUpsert;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.DeleteManyModel;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import idv.hsiehpinghan.mongodbassistant.assistant.CollectionAssistant;
import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.mongodbassistant.enumeration.Enumeration;
import idv.hsiehpinghan.mongodbassistant.utility.MongodbTestUtility;
import idv.hsiehpinghan.mongodbassistant.utility.MongodbUtility;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class QueryTest extends AbstractTestNGSpringContextTests {

	@BeforeClass
	public void beforeClass() {
		MongodbTestUtility.init(applicationContext);
		
	}

	@Test
	public void insertOne() throws Exception {
//		Document document = generateDocument(ID, INT, STRING);
//		assistant.insertOne(DATABASE_NAME, COLLECTION_NAME, document);
	}

	

}

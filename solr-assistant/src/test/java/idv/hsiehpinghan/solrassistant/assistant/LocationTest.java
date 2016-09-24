package idv.hsiehpinghan.solrassistant.assistant;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class LocationTest extends AbstractTestNGSpringContextTests {
	private static final String ID = String.valueOf(System.nanoTime());
	private static final String SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_POINT = "-90.57341 43.17614";
	private static final String SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_RECTANGLE = "-74.093 41.042 -69.347 44.558";
	private static final String SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_CIRCLE = "Circle(37.775,-122.419 d=20)";
	private static final String SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_POLYGON = "POLYGON((-10 30, -40 40, -10 -20, 40 20, 0 0, -10 30))";
	private SolrInputDocument solrInputDocument = generateSolrInputDocument();
	private SolrQuery solrQuery = generateSolrQuery();
	@Autowired
	private SolrAssistant solrAssistant;

	@BeforeClass
	public void beforeClass() throws Exception {
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);

		System.err.println(solrInputDocument.getFieldValue("id"));
	}

	@Test
	public void pointTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.addFilterQuery("id :" + solrInputDocument.getFieldValue("id"));
		solrQuery.addFilterQuery(
				"{!geofilt sfield=spatial_recursive_prefix_tree_field_type_point pt=43.17614,-90.57342 d=1}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	@Test
	public void rectangleTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.addFilterQuery("id :" + solrInputDocument.getFieldValue("id"));
		solrQuery.addFilterQuery(
				"{!geofilt sfield=spatial_recursive_prefix_tree_field_type_rectangle pt=42.8,-71.72 d=1}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	@Test
	public void circleTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.addFilterQuery("id :" + solrInputDocument.getFieldValue("id"));
		solrQuery.addFilterQuery(
				"{!geofilt sfield=spatial_recursive_prefix_tree_field_type_circle pt=37.776,-122.419 d=1}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

	@Test
	public void polygonTest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.addFilterQuery("id :" + solrInputDocument.getFieldValue("id"));
		solrQuery.addFilterQuery("{!geofilt sfield=spatial_recursive_prefix_tree_field_type_polygon pt=20,-20 d=1}");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = response.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
	}

//	@AfterClass
//	private void afterClass() throws Exception {
//		String id = String.valueOf(solrInputDocument.getFieldValue("id"));
//		UpdateResponse deleteResponse = solrAssistant.deleteById(id);
//		Assert.assertEquals(deleteResponse.getStatus(), 0);
//		QueryResponse queryResponse = solrAssistant.query(solrQuery);
//		Assert.assertEquals(queryResponse.getResults().size(), 0);
//	}

	private SolrInputDocument generateSolrInputDocument() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", ID);
		doc.setField("spatial_recursive_prefix_tree_field_type_point", SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_POINT);
		doc.setField("spatial_recursive_prefix_tree_field_type_rectangle",
				SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_RECTANGLE);
		doc.setField("spatial_recursive_prefix_tree_field_type_circle",
				SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_CIRCLE);
		doc.setField("spatial_recursive_prefix_tree_field_type_polygon",
				SPATIAL_RECURSIVE_PREFIX_TREE_FIELD_TYPE_POLYGON);
		return doc;
	}

	private SolrQuery generateSolrQuery() {
		SolrQuery query = new SolrQuery();
		query.setQuery("id :" + solrInputDocument.getFieldValue("id"));
		return query;
	}
}

package idv.hsiehpinghan.solrassistant7.assistant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant7.configuration.SpringConfiguration;
import idv.hsiehpinghan.solrassistant7.document.DefaultDocument;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SolrAssistantTest extends AbstractTestNGSpringContextTests {
	private static final String ID = String.valueOf(System.nanoTime());
	private static final String SKU = "sku";
	private static final String NAME = "name";
	private static final String MANU = "manu";
	private static final List<String> CAT = generateStringList("cat");
	private static final List<String> FEATURES = generateStringList("features");
	private static final String INCLUDES = "includes";
	private static final Float WEIGHT = Float.MAX_VALUE;
	private static final Float PRICE = Float.MAX_VALUE;
	private static final Integer POPULARITY = Integer.MAX_VALUE;
	private static final Boolean INSTOCK = Boolean.TRUE;
	private static final String STORE = "37.7752,-122.4232";
	private static final List<String> TITLE = generateStringList("title");
	private static final String SUBJECT = "subject";
	private static final String DESCRIPTION = "description";
	private static final String COMMENTS = "comments";
	private static final String AUTHOR = "author";
	private static final String KEYWORDS = "keywords";
	private static final String CATEGORY = "category";
	private static final String RESOURCENAME = "resourcename";
	private static final String URL = "url";
	private static final List<String> CONTENT_TYPE = generateStringList("content_type");
	private static final Date LAST_MODIFIED = new Date();
	private static final List<String> LINKS = generateStringList("links");
	private static final List<String> CONTENT = generateStringList("content");
	private static final List<String> TEXT = generateStringList("text");
	private static final List<String> TEXT_REV = generateStringList("text_rev");
	private static final String MANU_EXACT = "manu_exact";
	private static final String PAYLOADS = "payloads";
	private static final Integer DYNAMICFIELD_I = Integer.MAX_VALUE;
	private static final List<Integer> DYNAMICFIELD_IS = generateIntegerList();
	private static final String DYNAMICFIELD_S = "dynamicfield_s";
	private static final List<String> DYNAMICFIELD_SS = generateStringList("dynamicfield_ss");
	private static final Long DYNAMICFIELD_L = Long.MAX_VALUE;
	private static final List<Long> DYNAMICFIELD_LS = generateLongList();
	private static final String DYNAMICFIELD_T = "dynamicfield_t";
	private static final List<String> DYNAMICFIELD_TXT = generateStringList("dynamicfield_txt");
	private static final List<String> DYNAMICFIELD_EN = generateStringList("dynamicfield_en");
	private static final Boolean DYNAMICFIELD_B = Boolean.TRUE;
	private static final List<Boolean> DYNAMICFIELD_BS = generateBooleanList();
	private static final Float DYNAMICFIELD_F = Float.MAX_VALUE;
	private static final List<Float> DYNAMICFIELD_FS = generateFloatList();
	private static final Double DYNAMICFIELD_D = Double.MAX_VALUE;
	private static final List<Double> DYNAMICFIELD_DS = generateDoubleList();
	private static final Date DYNAMICFIELD_DT = new Date();
	private static final List<Date> DYNAMICFIELD_DTS = generateDateList();
	private static final String DYNAMICFIELD_P = "37.7752,-122.4232";
	private static final Object IGNORED_DYNAMICFIELD = "IGNORED_DYNAMICFIELD";
	private static final List<String> ATTR_DYNAMICFIELD = generateStringList("attr_dynamicfield");
	private static final String NEW_SKU = "new_sku";
	private SolrInputDocument solrInputDocument = generateSolrInputDocument();
	private MapSolrParams solrParams = generateMapSolrParams();
	private DefaultDocument defaultDocument = generateDefaultDocument();

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void add() throws Exception {
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);
	}

	@Test(dependsOnMethods = { "add" })
	public void query() throws Exception {
		assertSolrDocument(solrInputDocument.getFieldValue("sku"), solrInputDocument.getFieldValue("popularity"));
	}

	@Test(dependsOnMethods = { "query" })
	private void delete() throws Exception {
		String id = String.valueOf(solrInputDocument.getFieldValue("id"));
		UpdateResponse deleteResponse = solrAssistant.deleteById(id);
		Assert.assertEquals(deleteResponse.getStatus(), 0);
		QueryResponse queryResponse = solrAssistant.query(solrParams);
		Assert.assertEquals(queryResponse.getResults().size(), 0);
	}

	@Test(dependsOnMethods = { "delete" })
	public void addBean() throws Exception {
		UpdateResponse response = solrAssistant.addBean(defaultDocument);
		Assert.assertEquals(response.getStatus(), 0);
	}

	@Test(dependsOnMethods = { "addBean" })
	public void queryBean() throws Exception {
		assertSolrDocument(solrInputDocument.getFieldValue("sku"), solrInputDocument.getFieldValue("popularity"));
	}

	@Test(dependsOnMethods = { "queryBean" })
	public void atomicUpdates() throws Exception {
		atomicUpdates_set();
		atomicUpdates_inc();
	}

	private void atomicUpdates_inc() throws SolrServerException, IOException {
		int amount = -10;
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		solrInputDocument.addField("id", this.solrInputDocument.getFieldValue("id").toString());
		Map<String, Object> fieldModifierMap = new HashMap<>(1);
		fieldModifierMap.put("inc", amount);
		solrInputDocument.addField("popularity", fieldModifierMap);
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);
		assertSolrDocument(NEW_SKU, (Integer) (this.solrInputDocument.getFieldValue("popularity")) + amount);
	}

	private void atomicUpdates_set() throws SolrServerException, IOException {
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		solrInputDocument.addField("id", this.solrInputDocument.getFieldValue("id").toString());
		Map<String, Object> fieldModifierMap = new HashMap<>(1);
		fieldModifierMap.put("set", NEW_SKU);
		solrInputDocument.addField("sku", fieldModifierMap);
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);
		assertSolrDocument(NEW_SKU, this.solrInputDocument.getFieldValue("popularity"));
	}

	private void assertSolrDocument(Object sku, Object popularity) throws SolrServerException, IOException {
		QueryResponse queryResponse = solrAssistant.query(solrParams);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(String.valueOf(doc.getFieldValue("id")),
					String.valueOf(solrInputDocument.getFieldValue("id")));
			Assert.assertEquals(doc.getFieldValue("sku"), sku);
			Assert.assertEquals(doc.getFieldValue("name"), solrInputDocument.getFieldValue("name"));
			Assert.assertEquals(doc.getFieldValue("manu"), solrInputDocument.getFieldValue("manu"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("cat")),
					String.valueOf(solrInputDocument.getFieldValues("cat")));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("features")),
					String.valueOf(solrInputDocument.getFieldValues("features")));
			Assert.assertEquals(doc.getFieldValue("includes"), solrInputDocument.getFieldValue("includes"));
			Assert.assertEquals(doc.getFieldValue("weight"), solrInputDocument.getFieldValue("weight"));
			Assert.assertEquals(doc.getFieldValue("price"), solrInputDocument.getFieldValue("price"));
			Assert.assertEquals(doc.getFieldValue("popularity"), popularity);
			Assert.assertEquals(doc.getFieldValue("inStock"), solrInputDocument.getFieldValue("inStock"));
			Assert.assertEquals(doc.getFieldValue("store"), solrInputDocument.getFieldValue("store"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("title")),
					String.valueOf(solrInputDocument.getFieldValues("title")));
			Assert.assertEquals(doc.getFieldValue("subject"), solrInputDocument.getFieldValue("subject"));
			Assert.assertEquals(doc.getFieldValue("description"), solrInputDocument.getFieldValue("description"));
			Assert.assertEquals(doc.getFieldValue("comments"), solrInputDocument.getFieldValue("comments"));
			Assert.assertEquals(doc.getFieldValue("author"), solrInputDocument.getFieldValue("author"));
			Assert.assertEquals(doc.getFieldValue("keywords"), solrInputDocument.getFieldValue("keywords"));
			Assert.assertEquals(doc.getFieldValue("category"), solrInputDocument.getFieldValue("category"));
			Assert.assertEquals(doc.getFieldValue("resourcename"), solrInputDocument.getFieldValue("resourcename"));
			Assert.assertEquals(doc.getFieldValue("url"), solrInputDocument.getFieldValue("url"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("content_type")),
					String.valueOf(solrInputDocument.getFieldValues("content_type")));
			Assert.assertEquals(doc.getFieldValue("last_modified"), solrInputDocument.getFieldValue("last_modified"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("links")),
					String.valueOf(solrInputDocument.getFieldValues("links")));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("content")),
					String.valueOf(solrInputDocument.getFieldValues("content")));
			Assert.assertNull(doc.getFieldValues("text"));
			Assert.assertNull(doc.getFieldValue("text_rev"));
			Assert.assertNull(doc.getFieldValue("manu_exact"));
			Assert.assertEquals(doc.getFieldValue("payloads"), solrInputDocument.getFieldValue("payloads"));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_i"), solrInputDocument.getFieldValue("dynamicfield_i"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_is")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_is")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_s"), solrInputDocument.getFieldValue("dynamicfield_s"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_ss")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_ss")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_l"), solrInputDocument.getFieldValue("dynamicfield_l"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_ls")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_ls")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_t"), solrInputDocument.getFieldValue("dynamicfield_t"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_txt")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_txt")));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_en")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_en")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_b"), solrInputDocument.getFieldValue("dynamicfield_b"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_bs")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_bs")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_f"), solrInputDocument.getFieldValue("dynamicfield_f"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_fs")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_fs")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_d"), solrInputDocument.getFieldValue("dynamicfield_d"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_ds")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_ds")));
			Assert.assertNull(doc.getFieldValue("dynamicfield_coordinate"));
		}
	}

	// HttpSolrClient client = new HttpSolrClient("http://localhost:8983/solr");
	//
	// // create the document
	// SolrInputDocument sdoc = new SolrInputDocument();
	// sdoc.addField("id","book1");
	// Map<String,Object> fieldModifier = new HashMap<>(1);
	// fieldModifier.put("add","Cyberpunk");
	// sdoc.addField("cat", fieldModifier); // add the map as the field value
	//
	// client.add( sdoc ); // send it to the solr server
	//
	// client.close(); // shutdown client before we exit

	@Test
	public void learningToRank() throws Exception {
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("q", "test");
		queryParamMap.put("rq",
				"{!ltr model=myEfiModel efi.text=test efi.preferredManufacturer=Apache efi.fromMobile=0 efi.answer=13}");
		queryParamMap.put("fl", "id,cat,manu,score,[features]");
		MapSolrParams mapSolrParams = new MapSolrParams(queryParamMap);
		QueryResponse queryResponse = solrAssistant.query(mapSolrParams);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		for (SolrDocument solrDocument : solrDocumentList) {
			Assert.assertTrue(((Float) solrDocument.get("score")) > 0);
		}
	}

	private MapSolrParams generateMapSolrParams() {
		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("q", "id:" + solrInputDocument.getFieldValue("id").toString());
		MapSolrParams mapSolrParams = new MapSolrParams(queryParamMap);
		return mapSolrParams;
	}

	private SolrInputDocument generateSolrInputDocument() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", ID);
		doc.setField("sku", SKU);
		doc.setField("name", NAME);
		doc.setField("manu", MANU);
		doc.setField("cat", CAT);
		doc.setField("features", FEATURES);
		doc.setField("includes", INCLUDES);
		doc.setField("weight", WEIGHT);
		doc.setField("price", PRICE);
		doc.setField("popularity", POPULARITY);
		doc.setField("inStock", INSTOCK);
		doc.setField("store", STORE);
		doc.setField("title", TITLE);
		doc.setField("subject", SUBJECT);
		doc.setField("description", DESCRIPTION);
		doc.setField("comments", COMMENTS);
		doc.setField("author", AUTHOR);
		doc.setField("keywords", KEYWORDS);
		doc.setField("category", CATEGORY);
		doc.setField("resourcename", RESOURCENAME);
		doc.setField("url", URL);
		doc.setField("content_type", CONTENT_TYPE);
		doc.setField("last_modified", LAST_MODIFIED);
		doc.setField("links", LINKS);
		doc.setField("content", CONTENT);
		doc.setField("text", TEXT);
		doc.setField("text_rev", TEXT_REV);
		doc.setField("manu_exact", MANU_EXACT);
		doc.setField("payloads", PAYLOADS);
		doc.setField("dynamicfield_i", DYNAMICFIELD_I);
		doc.setField("dynamicfield_is", DYNAMICFIELD_IS);
		doc.setField("dynamicfield_s", DYNAMICFIELD_S);
		doc.setField("dynamicfield_ss", DYNAMICFIELD_SS);
		doc.setField("dynamicfield_l", DYNAMICFIELD_L);
		doc.setField("dynamicfield_ls", DYNAMICFIELD_LS);
		doc.setField("dynamicfield_t", DYNAMICFIELD_T);
		doc.setField("dynamicfield_txt", DYNAMICFIELD_TXT);
		doc.setField("dynamicfield_en", DYNAMICFIELD_EN);
		doc.setField("dynamicfield_b", DYNAMICFIELD_B);
		doc.setField("dynamicfield_bs", DYNAMICFIELD_BS);
		doc.setField("dynamicfield_f", DYNAMICFIELD_F);
		doc.setField("dynamicfield_fs", DYNAMICFIELD_FS);
		doc.setField("dynamicfield_d", DYNAMICFIELD_D);
		doc.setField("dynamicfield_ds", DYNAMICFIELD_DS);
		doc.setField("dynamicfield_dt", DYNAMICFIELD_DT);
		doc.setField("dynamicfield_dts", DYNAMICFIELD_DTS);
		doc.setField("dynamicfield_p", DYNAMICFIELD_P);
		doc.setField("ignored_dynamicfield", IGNORED_DYNAMICFIELD);
		doc.setField("attr_dynamicfield", ATTR_DYNAMICFIELD);
		return doc;
	}

	private DefaultDocument generateDefaultDocument() {
		DefaultDocument doc = new DefaultDocument(ID, SKU, NAME, MANU, CAT, FEATURES, INCLUDES, WEIGHT, PRICE,
				POPULARITY, INSTOCK, STORE, TITLE, SUBJECT, DESCRIPTION, COMMENTS, AUTHOR, KEYWORDS, CATEGORY,
				RESOURCENAME, URL, CONTENT_TYPE, LAST_MODIFIED, LINKS, CONTENT, TEXT, TEXT_REV, MANU_EXACT, PAYLOADS,
				DYNAMICFIELD_I, DYNAMICFIELD_IS, DYNAMICFIELD_S, DYNAMICFIELD_SS, DYNAMICFIELD_L, DYNAMICFIELD_LS,
				DYNAMICFIELD_T, DYNAMICFIELD_TXT, DYNAMICFIELD_EN, DYNAMICFIELD_B, DYNAMICFIELD_BS, DYNAMICFIELD_F,
				DYNAMICFIELD_FS, DYNAMICFIELD_D, DYNAMICFIELD_DS, DYNAMICFIELD_DT, DYNAMICFIELD_DTS, DYNAMICFIELD_P,
				IGNORED_DYNAMICFIELD, ATTR_DYNAMICFIELD);
		return doc;
	}

	private static List<String> generateStringList(String name) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 3; ++i) {
			list.add(name + i);
		}
		return list;
	}

	private static List<Integer> generateIntegerList() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; ++i) {
			list.add(i);
		}
		return list;
	}

	private static List<Long> generateLongList() {
		List<Long> list = new ArrayList<Long>();
		for (long i = 0; i < 3; ++i) {
			list.add(i);
		}
		return list;
	}

	private static List<Boolean> generateBooleanList() {
		List<Boolean> list = new ArrayList<Boolean>();
		for (long i = 0; i < 3; ++i) {
			list.add(Boolean.TRUE);
		}
		return list;
	}

	private static List<Float> generateFloatList() {
		List<Float> list = new ArrayList<Float>();
		for (long i = 0; i < 3; ++i) {
			list.add(Float.valueOf(i));
		}
		return list;
	}

	private static List<Double> generateDoubleList() {
		List<Double> list = new ArrayList<Double>();
		for (long i = 0; i < 3; ++i) {
			list.add(Double.valueOf(i));
		}
		return list;
	}

	private static List<Date> generateDateList() {
		Date now = new Date();
		List<Date> list = new ArrayList<Date>();
		for (int i = 0; i < 3; ++i) {
			list.add(DateUtils.addDays(now, i));
		}
		return list;
	}
}

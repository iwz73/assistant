package idv.hsiehpinghan.solrassistant7.assistant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
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

import com.fasterxml.jackson.databind.ObjectMapper;

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
	private static final String DYNAMICFIELD_S = "dynamicField_s";
	private static final List<String> DYNAMICFIELD_SS = generateStringList("dynamicField_ss");
	private static final Long DYNAMICFIELD_L = Long.MAX_VALUE;
	private static final List<Long> DYNAMICFIELD_LS = generateLongList();
	private static final String DYNAMICFIELD_T = "dynamicField_t";
	private static final List<String> DYNAMICFIELD_TXT = generateStringList("dynamicField_txt");
	private static final List<String> DYNAMICFIELD_EN = generateStringList("dynamicField_en");
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
	private SolrInputDocument solrInputDocument = generateSolrInputDocument();
	private MapSolrParams solrParams = generateMapSolrParams();
	private DefaultDocument defaultDocument = generateDefaultDocument();

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void add() throws Exception {
		UpdateResponse response = solrAssistant.add(solrInputDocument);
		Assert.assertEquals(response.getStatus(), 0);
	}

	@Test(dependsOnMethods = { "add" })
	public void query() throws Exception {
		QueryResponse queryResponse = solrAssistant.query(solrParams);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		Assert.assertEquals(solrDocumentList.size(), 1);
		for (SolrDocument doc : solrDocumentList) {
			Assert.assertEquals(String.valueOf(doc.getFieldValue("id")),
					String.valueOf(solrInputDocument.getFieldValue("id")));
			Assert.assertEquals(doc.getFieldValue("sku"), solrInputDocument.getFieldValue("sku"));
			Assert.assertEquals(doc.getFieldValue("name"), solrInputDocument.getFieldValue("name"));
			Assert.assertEquals(doc.getFieldValue("manu"), solrInputDocument.getFieldValue("manu"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("cat")),
					String.valueOf(solrInputDocument.getFieldValues("cat")));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("features")),
					String.valueOf(solrInputDocument.getFieldValues("features")));
			Assert.assertEquals(doc.getFieldValue("includes"), solrInputDocument.getFieldValue("includes"));
			Assert.assertEquals(doc.getFieldValue("weight"), solrInputDocument.getFieldValue("weight"));
			Assert.assertEquals(doc.getFieldValue("price"), solrInputDocument.getFieldValue("price"));
			Assert.assertEquals(doc.getFieldValue("popularity"), solrInputDocument.getFieldValue("popularity"));
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
			Assert.assertEquals(doc.getFieldValue("dynamicField_s"), solrInputDocument.getFieldValue("dynamicField_s"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicField_ss")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicField_ss")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_l"), solrInputDocument.getFieldValue("dynamicfield_l"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_ls")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_ls")));
			Assert.assertEquals(doc.getFieldValue("dynamicfield_t"), solrInputDocument.getFieldValue("dynamicfield_t"));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicfield_txt")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_txt")));
			Assert.assertEquals(String.valueOf(doc.getFieldValues("dynamicField_en")),
					String.valueOf(solrInputDocument.getFieldValues("dynamicField_en")));
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
		QueryResponse response = solrAssistant.query(solrParams);
		List<DefaultDocument> defaultDocumentList = response.getBeans(DefaultDocument.class);
		Assert.assertEquals(defaultDocumentList.size(), 1);
		for (DefaultDocument doc : defaultDocumentList) {
			Assert.assertEquals(String.valueOf(doc.getId()), String.valueOf(solrInputDocument.getFieldValue("id")));
			Assert.assertEquals(doc.getSku(), solrInputDocument.getFieldValue("sku"));
			Assert.assertEquals(doc.getName(), solrInputDocument.getFieldValue("name"));
			Assert.assertEquals(doc.getManu(), solrInputDocument.getFieldValue("manu"));
			Assert.assertEquals(String.valueOf(doc.getCat()), String.valueOf(solrInputDocument.getFieldValues("cat")));
			Assert.assertEquals(String.valueOf(doc.getFeatures()),
					String.valueOf(solrInputDocument.getFieldValues("features")));
			Assert.assertEquals(doc.getIncludes(), solrInputDocument.getFieldValue("includes"));
			Assert.assertEquals(doc.getWeight(), solrInputDocument.getFieldValue("weight"));
			Assert.assertEquals(doc.getPrice(), solrInputDocument.getFieldValue("price"));
			Assert.assertEquals(doc.getPopularity(), solrInputDocument.getFieldValue("popularity"));
			Assert.assertEquals(doc.getInStock(), solrInputDocument.getFieldValue("inStock"));
			Assert.assertEquals(doc.getStore(), solrInputDocument.getFieldValue("store"));
			Assert.assertEquals(String.valueOf(doc.getTitle()),
					String.valueOf(solrInputDocument.getFieldValues("title")));
			Assert.assertEquals(doc.getSubject(), solrInputDocument.getFieldValue("subject"));
			Assert.assertEquals(doc.getDescription(), solrInputDocument.getFieldValue("description"));
			Assert.assertEquals(doc.getComments(), solrInputDocument.getFieldValue("comments"));
			Assert.assertEquals(doc.getAuthor(), solrInputDocument.getFieldValue("author"));
			Assert.assertEquals(doc.getKeywords(), solrInputDocument.getFieldValue("keywords"));
			Assert.assertEquals(doc.getCategory(), solrInputDocument.getFieldValue("category"));
			Assert.assertEquals(doc.getResourcename(), solrInputDocument.getFieldValue("resourcename"));
			Assert.assertEquals(doc.getUrl(), solrInputDocument.getFieldValue("url"));
			Assert.assertEquals(String.valueOf(doc.getContent_type()),
					String.valueOf(solrInputDocument.getFieldValues("content_type")));
			Assert.assertEquals(doc.getLast_modified(), solrInputDocument.getFieldValue("last_modified"));
			Assert.assertEquals(String.valueOf(doc.getLinks()),
					String.valueOf(solrInputDocument.getFieldValues("links")));
			Assert.assertEquals(String.valueOf(doc.getContent()),
					String.valueOf(solrInputDocument.getFieldValues("content")));
			Assert.assertNull(doc.getText());
			Assert.assertNull(doc.getText_rev());
			Assert.assertNull(doc.getManu_exact());
			Assert.assertEquals(doc.getPayloads(), solrInputDocument.getFieldValue("payloads"));
			Assert.assertEquals(doc.getDynamicfield_i(), solrInputDocument.getFieldValue("dynamicfield_i"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_is()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_is")));
			Assert.assertEquals(doc.getDynamicfield_s(), solrInputDocument.getFieldValue("dynamicField_s"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_ss()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicField_ss")));
			Assert.assertEquals(doc.getDynamicfield_l(), solrInputDocument.getFieldValue("dynamicfield_l"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_ls()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_ls")));
			Assert.assertEquals(doc.getDynamicfield_t(), solrInputDocument.getFieldValue("dynamicfield_t"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_txt()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_txt")));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_en()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicField_en")));
			Assert.assertEquals(doc.getDynamicfield_b(), solrInputDocument.getFieldValue("dynamicfield_b"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_bs()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_bs")));
			Assert.assertEquals(doc.getDynamicfield_f(), solrInputDocument.getFieldValue("dynamicfield_f"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_fs()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_fs")));
			Assert.assertEquals(doc.getDynamicfield_d(), solrInputDocument.getFieldValue("dynamicfield_d"));
			Assert.assertEquals(String.valueOf(doc.getDynamicfield_ds()),
					String.valueOf(solrInputDocument.getFieldValues("dynamicfield_ds")));
		}
	}

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
		doc.setField("dynamicField_s", DYNAMICFIELD_S);
		doc.setField("dynamicField_ss", DYNAMICFIELD_SS);
		doc.setField("dynamicfield_l", DYNAMICFIELD_L);
		doc.setField("dynamicfield_ls", DYNAMICFIELD_LS);
		doc.setField("dynamicfield_t", DYNAMICFIELD_T);
		doc.setField("dynamicfield_txt", DYNAMICFIELD_TXT);
		doc.setField("dynamicField_en", DYNAMICFIELD_EN);
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
		DefaultDocument doc = new DefaultDocument();
		doc.setId(ID);
		doc.setSku(SKU);
		doc.setName(NAME);
		doc.setManu(MANU);
		doc.setCat(CAT);
		doc.setFeatures(FEATURES);
		doc.setIncludes(INCLUDES);
		doc.setWeight(WEIGHT);
		doc.setPrice(PRICE);
		doc.setPopularity(POPULARITY);
		doc.setInStock(INSTOCK);
		doc.setStore(STORE);
		doc.setTitle(TITLE);
		doc.setSubject(SUBJECT);
		doc.setDescription(DESCRIPTION);
		doc.setComments(COMMENTS);
		doc.setAuthor(AUTHOR);
		doc.setKeywords(KEYWORDS);
		doc.setCategory(CATEGORY);
		doc.setResourcename(RESOURCENAME);
		doc.setUrl(URL);
		doc.setContent_type(CONTENT_TYPE);
		doc.setLast_modified(LAST_MODIFIED);
		doc.setLinks(LINKS);
		doc.setContent(CONTENT);
		doc.setText(TEXT);
		doc.setText_rev(TEXT_REV);
		doc.setManu_exact(MANU_EXACT);
		doc.setPayloads(PAYLOADS);
		doc.setDynamicfield_i(DYNAMICFIELD_I);
		doc.setDynamicfield_is(DYNAMICFIELD_IS);
		doc.setDynamicfield_s(DYNAMICFIELD_S);
		doc.setDynamicfield_ss(DYNAMICFIELD_SS);
		doc.setDynamicfield_l(DYNAMICFIELD_L);
		doc.setDynamicfield_ls(DYNAMICFIELD_LS);
		doc.setDynamicfield_t(DYNAMICFIELD_T);
		doc.setDynamicfield_txt(DYNAMICFIELD_TXT);
		doc.setDynamicfield_en(DYNAMICFIELD_EN);
		doc.setDynamicfield_b(DYNAMICFIELD_B);
		doc.setDynamicfield_bs(DYNAMICFIELD_BS);
		doc.setDynamicfield_f(DYNAMICFIELD_F);
		doc.setDynamicfield_fs(DYNAMICFIELD_FS);
		doc.setDynamicfield_d(DYNAMICFIELD_D);
		doc.setDynamicfield_ds(DYNAMICFIELD_DS);
		doc.setDynamicfield_dt(DYNAMICFIELD_DT);
		doc.setDynamicfield_dts(DYNAMICFIELD_DTS);
		doc.setDynamicfield_p(DYNAMICFIELD_P);
		doc.setIgnored_dynamicfield(IGNORED_DYNAMICFIELD);
		doc.setAttr_dynamicfield(ATTR_DYNAMICFIELD);
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

package idv.hsiehpinghan.elasticsearchassistant.assistant;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import idv.hsiehpinghan.elasticsearchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ElasticsearchAssistantTest extends AbstractTestNGSpringContextTests {
	private final String INDEX = "elasticsearch_index";
	private final String TYPE = "elasticsearch_type";
	private final String ID = "elasticsearch_id";
	private final String BIGDECIMAL_NAME = "BIGDECIMAL_NAME";
	private final BigDecimal BIGDECIMAL = BigDecimal.ONE;
	private final String BOOLEAN_NAME = "BOOLEAN_NAME";
	private final Boolean BOOLEAN = Boolean.TRUE;
	private final String PRIMARY_BOOLEAN_NAME = "PRIMARY_BOOLEAN_NAME";
	private final boolean PRIMARY_BOOLEAN = true;
	private final String BYTE_ARRAY_NAME = "BYTE_ARRAY_NAME";
	private final byte[] BYTE_ARRAY = generateByteArray();
	private final String PRIMARY_DOUBLE_NAME = "PRIMARY_DOUBLE_NAME";
	private final double PRIMARY_DOUBLE = 1.1;
	private final String DOUBLE_NAME = "DOUBLE_NAME";
	private final Double DOUBLE = 2.2;
	private final String FLOAT_NAME = "FLOAT_NAME";
	private final Float FLOAT = 3.3f;
	private final String PRIMARY_FLOAT_NAME = "PRIMARY_FLOAT_NAME";
	private final float PRIMARY_FLOAT = 4.4f;
	private final String PRIMARY_INT_NAME = "PRIMARY_INT_NAME";
	private final int PRIMARY_INT = 5;
	private final String INTEGER_NAME = "INTEGER_NAME";
	private final Integer INTEGER = 6;
	private final String JSON_NODE_NAME = "JSON_NODE_NAME";
	private final JsonNode JSON_NODE = generateJsonNode();
	private final String PRIMARY_LONG_NAME = "PRIMARY_LONG_NAME";
	private final long PRIMARY_LONG = 7;
	private final String LONG_NAME = "LONG_NAME";
	private final Long LONG = 8L;
	private final String SHORT_NAME = "SHORT_NAME";
	private final Short SHORT = 9;
	private final String PRIMARY_SHORT_NAME = "PRIMARY_SHORT_NAME";
	private final short PRIMARY_SHORT = 10;
	private final String STRING_NAME = "STRING_NAME";
	private final String STRING = "string";
	private final String ARRAY_NAME = "ARRAY_NAME";
	private final String SIMPLIFIED_STRING_NAME = "SIMPLIFIED_STRING_NAME";
	private final String SIMPLIFIED_STRING = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
	private ArrayNode arrayNode = null;

	@Autowired
	private ElasticsearchAssistant assistant;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		DeleteIndexResponse deleteIndexResponse = assistant.prepareDelete(INDEX);
		Assert.assertTrue(deleteIndexResponse.isAcknowledged());
	}

	@Test
	public void prepareCreate() throws Exception {
		CreateIndexResponse createIndexResponse = assistant.prepareCreate(INDEX);
		Assert.assertTrue(createIndexResponse.isAcknowledged());
	}

	@Test(dependsOnMethods = { "prepareCreate" })
	public void preparePutMapping() throws Exception {
		String source = generateMappingSource();
		PutMappingResponse putMappingResponse = assistant.preparePutMapping(INDEX, TYPE, source);
		Assert.assertTrue(putMappingResponse.isAcknowledged());
	}

	@Test(dependsOnMethods = { "preparePutMapping" })
	public void prepareGetMappings() throws Exception {
		GetMappingsResponse getMappingsResponse = assistant.prepareGetMappings(INDEX, TYPE);
		ImmutableOpenMap<String, MappingMetaData> map = getMappingsResponse.getMappings().get(INDEX);
		Assert.assertTrue(map.size() > 0);
		for (ObjectObjectCursor<String, MappingMetaData> cursor : map) {
			System.out.println(cursor.key + " = " + cursor.value.source());
		}
	}

	@Test(dependsOnMethods = { "prepareGetMappings" })
	public void prepareIndex() throws Exception {
		String source = generateSource(STRING);
		IndexResponse indexResponse = assistant.prepareIndex(INDEX, TYPE, ID, source);
		assistant.flush(INDEX, true);
		Assert.assertEquals(indexResponse.getId(), ID);
	}

	@Test(dependsOnMethods = { "prepareIndex" })
	public void prepareGet() throws Exception {
		GetResponse getResponse = assistant.prepareGet(INDEX, TYPE, ID);
		String result = getResponse.getSourceAsString();
		validateObjectNode((ObjectNode) objectMapper.readTree(result));
	}

	@Test(dependsOnMethods = { "prepareGet" })
	public void prepareUpdate() throws Exception {
		String updatedString = "updated string";
		String source = generateSource(updatedString);
		UpdateResponse updateResponse = assistant.prepareUpdate(INDEX, TYPE, ID, source);
		assistant.flush(INDEX, true);
		Assert.assertEquals(updateResponse.getId(), ID);
		GetResponse getResponse = assistant.prepareGet(INDEX, TYPE, ID);
		String result = getResponse.getSourceAsString();
		JsonNode jsonNode = objectMapper.readTree(result);
		Assert.assertEquals(jsonNode.get(STRING_NAME).textValue(), updatedString);
	}

	@Test(dependsOnMethods = { "prepareUpdate" })
	public void prepareDelete() throws Exception {
		DeleteResponse deleteResponse = assistant.prepareDelete(INDEX, TYPE, ID);
		assistant.flush(INDEX, true);
		Assert.assertTrue(deleteResponse.isFound());
	}

	@Test(dependsOnMethods = { "prepareDelete" })
	public void prepareBulk() throws Exception {
		String source = generateSource(STRING);
		BulkResponse bulkResponse = assistant.prepareBulk(INDEX, TYPE, ID, source);
		assistant.flush(INDEX, true);
		Assert.assertFalse(bulkResponse.hasFailures());
	}

	@Test(dependsOnMethods = { "prepareBulk" })
	public void prepareSearchByQuery() throws Exception {
		testMatchAllQuery();
		testMultiMatchQuery();
		testBoolQuery();
		testTermQuery();
		testWildcardQuery();
		testQueryStringQuery();
		testMoreLikeThisQuery();
	}

	@Test(dependsOnMethods = { "prepareSearchByQuery" })
	public void prepareSearchByQueryWithHighlight() throws Exception {
		QueryBuilder queryBuilder = QueryBuilders.termQuery(SIMPLIFIED_STRING_NAME, "自由");
		String highlighterPreTags = "<span>";
		String highlighterPostTags = "</span>";
		SearchResponse searchResponse = assistant.prepareSearchByQueryWithHighlight(INDEX, TYPE, queryBuilder,
				SIMPLIFIED_STRING_NAME, highlighterPreTags, highlighterPostTags);
		for (SearchHit searchHit : searchResponse.getHits()) {
			Map<String, HighlightField> map = searchHit.getHighlightFields();
			HighlightField highlightField = map.get(SIMPLIFIED_STRING_NAME);
			Assert.assertEquals(highlightField.toString(),
					"[SIMPLIFIED_STRING_NAME], fragments[[欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更<span>自由</span>!]]");
		}
	}

	@Test(dependsOnMethods = { "prepareSearchByQueryWithHighlight" })
	public void prepareSearchByPostFilter() throws Exception {
		testTermFilter();
		testExistsFilter();
		testMatchAllFilter();
		testQueryStringFilter();
		testRangeFilter();
		testBoolFilter();
	}

	@Test(dependsOnMethods = { "prepareSearchByPostFilter" })
	public void prepareSearchByAggregation() throws Exception {
		testAvgAggregation();
	}

	@Test(dependsOnMethods = { "prepareSearchByAggregation" })
	public void prepareMultiSearch() throws Exception {
		MultiSearchResponse multiSearchResponse = assistant.prepareMultiSearch(INDEX, TYPE, STRING_NAME, STRING,
				PRIMARY_INT_NAME, PRIMARY_INT);
		for (MultiSearchResponse.Item item : multiSearchResponse.getResponses()) {
			Assert.assertTrue(item.getResponse().getHits().getTotalHits() > 0);
		}
	}

	private void testMatchAllQuery() {
		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testMultiMatchQuery() {
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(PRIMARY_INT, PRIMARY_INT_NAME, INTEGER_NAME);
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testBoolQuery() {
		QueryBuilder stringQueryBuilder = QueryBuilders.termQuery(STRING_NAME, STRING);
		QueryBuilder integerQueryBuilder = QueryBuilders.termQuery(INTEGER_NAME, 0);
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(stringQueryBuilder).mustNot(integerQueryBuilder);
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testTermQuery() {
		QueryBuilder queryBuilder = QueryBuilders.termQuery(STRING_NAME, STRING);
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testWildcardQuery() {
		QueryBuilder queryBuilder = QueryBuilders.wildcardQuery(STRING_NAME, "str?ng");
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testQueryStringQuery() {
		QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("+自由 +中文");
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testMoreLikeThisQuery() {
		QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery(SIMPLIFIED_STRING_NAME).like("自由中文").minTermFreq(1)
				.minDocFreq(1);
		SearchResponse searchResponse = assistant.prepareSearchByQuery(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testTermFilter() {
		QueryBuilder queryBuilder = QueryBuilders.termQuery(STRING_NAME, STRING);
		SearchResponse searchResponse = assistant.prepareSearchByPostFilter(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testExistsFilter() {
		QueryBuilder queryBuilder = QueryBuilders.existsQuery(STRING_NAME);
		SearchResponse searchResponse = assistant.prepareSearchByPostFilter(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testMatchAllFilter() {
		QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
		SearchResponse searchResponse = assistant.prepareSearchByPostFilter(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testQueryStringFilter() {
		QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("+自由 +中文");
		SearchResponse searchResponse = assistant.prepareSearchByPostFilter(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testRangeFilter() {
		QueryBuilder queryBuilder = QueryBuilders.rangeQuery(INTEGER_NAME).from(0).to(10).includeLower(true)
				.includeUpper(false);
		SearchResponse searchResponse = assistant.prepareSearchByPostFilter(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testBoolFilter() {
		QueryBuilder stringQueryBuilder = QueryBuilders.termQuery(STRING_NAME, STRING);
		QueryBuilder integerQueryBuilder = QueryBuilders.termQuery(INTEGER_NAME, 0);
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(stringQueryBuilder).mustNot(integerQueryBuilder);
		SearchResponse searchResponse = assistant.prepareSearchByPostFilter(INDEX, TYPE, queryBuilder);
		Assert.assertTrue(searchResponse.getHits().getTotalHits() > 0);
	}

	private void testAvgAggregation() {
		final String AGGREGATION_NAME = "aggregation name";
		AbstractAggregationBuilder abstractAggregationBuilder = AggregationBuilders.avg(AGGREGATION_NAME)
				.field(INTEGER_NAME);
		SearchResponse searchResponse = assistant.prepareSearchByAggregation(INDEX, TYPE, abstractAggregationBuilder);
		Aggregation aggregation = searchResponse.getAggregations().get(AGGREGATION_NAME);
		Double actual = (Double) aggregation.getProperty("value");
		Assert.assertEquals(actual, Double.valueOf("6.0"));
	}

	private String generateMappingSource() {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.set(TYPE, generateMappingType());
		return objectNode.toString();
	}

	private ObjectNode generateMappingType() {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.set("properties", generateMappingProperties());
		return objectNode;
	}

	private ObjectNode generateMappingProperties() {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.set(SIMPLIFIED_STRING_NAME, generateMappingSimplifiedString());
		return objectNode;
	}

	private ObjectNode generateMappingSimplifiedString() {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put("type", "string");
		objectNode.put("store", "true");
		objectNode.put("analyzer", "index_ansj");
		objectNode.put("search_analyzer", "query_ansj");
		return objectNode;
	}

	private String generateSource(String string) throws IOException {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put(BIGDECIMAL_NAME, BIGDECIMAL);
		objectNode.put(BOOLEAN_NAME, BOOLEAN);
		objectNode.put(PRIMARY_BOOLEAN_NAME, PRIMARY_BOOLEAN);
		objectNode.put(BYTE_ARRAY_NAME, BYTE_ARRAY);
		objectNode.put(PRIMARY_DOUBLE_NAME, PRIMARY_DOUBLE);
		objectNode.put(DOUBLE_NAME, DOUBLE);
		objectNode.put(FLOAT_NAME, FLOAT);
		objectNode.put(PRIMARY_FLOAT_NAME, PRIMARY_FLOAT);
		objectNode.put(PRIMARY_INT_NAME, PRIMARY_INT);
		objectNode.put(INTEGER_NAME, INTEGER);
		objectNode.put(PRIMARY_LONG_NAME, PRIMARY_LONG);
		objectNode.put(LONG_NAME, LONG);
		objectNode.put(SHORT_NAME, SHORT);
		objectNode.put(PRIMARY_SHORT_NAME, PRIMARY_SHORT);
		objectNode.put(STRING_NAME, string);
		objectNode.set(JSON_NODE_NAME, JSON_NODE);
		arrayNode = objectNode.putArray(ARRAY_NAME);
		appendArrayNode(arrayNode);
		objectNode.put(SIMPLIFIED_STRING_NAME, SIMPLIFIED_STRING);
		return objectNode.toString();
	}

	private void validateObjectNode(ObjectNode objectNode) throws IOException {
		Assert.assertEquals(objectNode.get(BIGDECIMAL_NAME).decimalValue(), BIGDECIMAL);
		Assert.assertEquals(objectNode.get(BOOLEAN_NAME).booleanValue(), BOOLEAN.booleanValue());
		Assert.assertEquals(objectNode.get(PRIMARY_BOOLEAN_NAME).booleanValue(), PRIMARY_BOOLEAN);
		Assert.assertEquals(objectNode.get(BYTE_ARRAY_NAME).binaryValue(), BYTE_ARRAY);
		Assert.assertEquals(objectNode.get(PRIMARY_DOUBLE_NAME).doubleValue(), PRIMARY_DOUBLE);
		Assert.assertEquals(objectNode.get(DOUBLE_NAME).doubleValue(), DOUBLE);
		Assert.assertEquals(objectNode.get(FLOAT_NAME).floatValue(), FLOAT);
		Assert.assertEquals(objectNode.get(PRIMARY_FLOAT_NAME).floatValue(), PRIMARY_FLOAT);
		Assert.assertEquals(objectNode.get(PRIMARY_INT_NAME).intValue(), PRIMARY_INT);
		Assert.assertEquals(objectNode.get(INTEGER_NAME).intValue(), INTEGER.intValue());
		Assert.assertEquals(objectNode.get(PRIMARY_LONG_NAME).longValue(), PRIMARY_LONG);
		Assert.assertEquals(objectNode.get(LONG_NAME).longValue(), LONG.longValue());
		Assert.assertEquals(objectNode.get(SHORT_NAME).shortValue(), SHORT.shortValue());
		Assert.assertEquals(objectNode.get(PRIMARY_SHORT_NAME).shortValue(), PRIMARY_SHORT);
		Assert.assertEquals(objectNode.get(STRING_NAME).textValue(), STRING);
		Assert.assertEquals(objectNode.get(JSON_NODE_NAME), JSON_NODE);
		Assert.assertEquals(objectNode.get(ARRAY_NAME).toString(), arrayNode.toString());
		Assert.assertEquals(objectNode.get(SIMPLIFIED_STRING_NAME).textValue(), SIMPLIFIED_STRING);
	}

	private void appendArrayNode(ArrayNode arrayNode) {
		for (int i = 0; i < 3; ++i) {
			arrayNode.add(i);
		}
	}

	private JsonNode generateJsonNode() {
		final String TEXT = "text";
		final String TEXT_VALUE = "text value";
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put(TEXT, TEXT_VALUE);
		return (JsonNode) objectNode;
	}

	private byte[] generateByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}
}

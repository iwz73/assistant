package idv.hsiehpinghan.elasticsearchassistant.assistant;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.flush.FlushResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchAssistant {
	@Autowired
	private Client client;

	public CreateIndexResponse prepareCreate(String index) {
		CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(index);
		return createIndexRequestBuilder.execute().actionGet();
	}

	public DeleteIndexResponse prepareDelete(String index) {
		DeleteIndexRequestBuilder DeleteIndexRequestBuilder = client.admin().indices().prepareDelete(index);
		return DeleteIndexRequestBuilder.execute().actionGet();
	}

	public PutMappingResponse preparePutMapping(String index, String type, String source) {
		PutMappingRequestBuilder putMappingRequestBuilder = client.admin().indices().preparePutMapping(index);
		putMappingRequestBuilder.setType(type);
		putMappingRequestBuilder.setSource(source);
		return putMappingRequestBuilder.execute().actionGet();
	}

	public GetMappingsResponse prepareGetMappings(String index, String type) {
		GetMappingsRequestBuilder getMappingsRequestBuilder = client.admin().indices().prepareGetMappings(index);
		return getMappingsRequestBuilder.execute().actionGet();
	}

	public IndexResponse prepareIndex(String index, String type, String id, String source) {
		IndexRequestBuilder indexRequestBuilder = client.prepareIndex(index, type, id);
		indexRequestBuilder.setSource(source);
		return indexRequestBuilder.execute().actionGet();
	}

	public GetResponse prepareGet(String index, String type, String id) {
		GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
		return getRequestBuilder.execute().actionGet();
	}

	public UpdateResponse prepareUpdate(String index, String type, String id, String source) throws Exception {
		UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(index, type, id);
		updateRequestBuilder.setDoc(source);
		return updateRequestBuilder.execute().actionGet();
	}

	public DeleteResponse prepareDelete(String index, String type, String id) {
		DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(index, type, id);
		return deleteRequestBuilder.execute().actionGet();
	}

	public BulkResponse prepareBulk(String index, String type, String id, String source) {
		BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
		for (int i = 0; i < 3; ++i) {
			IndexRequestBuilder indexRequestBuilder = client.prepareIndex(index, type, id + i);
			indexRequestBuilder.setSource(source);
			bulkRequestBuilder.add(indexRequestBuilder);
		}
		return bulkRequestBuilder.execute().actionGet();
	}

	public SearchResponse prepareSearchByQuery(String index, String type, QueryBuilder queryBuilder) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		searchRequestBuilder.setQuery(queryBuilder);
		return searchRequestBuilder.execute().actionGet();
	}

	public SearchResponse prepareSearchByPostFilter(String index, String type, QueryBuilder queryBuilder) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		searchRequestBuilder.setPostFilter(queryBuilder);
		return searchRequestBuilder.execute().actionGet();
	}

	public SearchResponse prepareSearchByAggregation(String index, String type,
			AbstractAggregationBuilder abstractAggregationBuilder) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		searchRequestBuilder.addAggregation(abstractAggregationBuilder);
		return searchRequestBuilder.execute().actionGet();
	}

	public MultiSearchResponse prepareMultiSearch(String index, String type, String name_0, String value_0,
			String name_1, int value_1) {
		MultiSearchRequestBuilder multiSearchRequestBuilder = client.prepareMultiSearch();
		multiSearchRequestBuilder.add(generateSearchRequestBuilder_0(index, type, name_0, value_0));
		multiSearchRequestBuilder.add(generateSearchRequestBuilder_1(index, type, name_1, value_1));
		return multiSearchRequestBuilder.execute().actionGet();
	}

	public FlushResponse flush(String index, boolean isForce) {
		FlushRequest flushRequest = new FlushRequest(index).force(isForce);
		return client.admin().indices().flush(flushRequest).actionGet();
	}

	private SearchRequestBuilder generateSearchRequestBuilder_0(String index, String type, String name, String value) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		QueryBuilder queryBuilder = QueryBuilders.termQuery(name, value);
		searchRequestBuilder.setQuery(queryBuilder);
		return searchRequestBuilder;
	}

	private SearchRequestBuilder generateSearchRequestBuilder_1(String index, String type, String name, int value) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		QueryBuilder queryBuilder = QueryBuilders.termQuery(name, value);
		searchRequestBuilder.setQuery(queryBuilder);
		return searchRequestBuilder;
	}
}

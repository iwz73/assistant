package idv.hsiehpinghan.elasticsearchassistant.assistant;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchAssistant {
	@Autowired
	private Client client;

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

	public SearchResponse prepareSearch(String index, String type, String name, String value) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		QueryBuilder queryBuilder = QueryBuilders.termQuery(name, value);
		searchRequestBuilder.setQuery(queryBuilder);
		return searchRequestBuilder.execute().actionGet();
	}

	public MultiSearchResponse prepareMultiSearch(String index, String type, String name_0, String value_0,
			String name_1, int value_1) {
		MultiSearchRequestBuilder multiSearchRequestBuilder = client.prepareMultiSearch();
		multiSearchRequestBuilder.add(generateSearchRequestBuilder_0(index, type, name_0, value_0));
		multiSearchRequestBuilder.add(generateSearchRequestBuilder_1(index, type, name_1, value_1));
		return multiSearchRequestBuilder.execute().actionGet();
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

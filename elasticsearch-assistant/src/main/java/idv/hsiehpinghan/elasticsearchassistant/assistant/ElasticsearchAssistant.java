package idv.hsiehpinghan.elasticsearchassistant.assistant;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchAssistant {
	@Autowired
	private Client client;

	public IndexResponse prepareIndex(String index, String type, String id, String source) {
		IndexRequestBuilder indexRequestBuilder = client.prepareIndex(index, type, id);
		return indexRequestBuilder.setSource(source).execute().actionGet();
	}
}

package idv.hsiehpinghan.solrassistant7.assistant;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.solrassistant7.document.DefaultDocument;

@Component
public class SolrAssistant {
	@Autowired
	@Qualifier("queryHttpSolrClient")
	private HttpSolrClient queryHttpSolrClient;
	/**
	 * Caution : not thread-safe !!!
	 */
	@Autowired
	@Qualifier("updateHttpSolrClient")
	HttpSolrClient updateHttpSolrClient;

	public QueryResponse query(SolrParams params) throws SolrServerException, IOException {
		return queryHttpSolrClient.query(params);
	}

	public UpdateResponse add(SolrInputDocument solrInputDocument) throws SolrServerException, IOException {
		updateHttpSolrClient.add(solrInputDocument);
		return updateHttpSolrClient.commit();
	}

	public UpdateResponse addBean(DefaultDocument document) throws IOException, SolrServerException {
		updateHttpSolrClient.addBean(document);
		return updateHttpSolrClient.commit();
	}

	public UpdateResponse deleteById(String id) throws SolrServerException, IOException {
		updateHttpSolrClient.deleteById(id);
		return updateHttpSolrClient.commit();
	}
}

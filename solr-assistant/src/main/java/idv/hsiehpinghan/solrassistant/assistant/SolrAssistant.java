package idv.hsiehpinghan.solrassistant.assistant;

import idv.hsiehpinghan.solrassistant.document.BasicDocument;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolrAssistant {
	@Autowired
	private HttpSolrServer httpSolrServer;

	public UpdateResponse add(SolrInputDocument solrInputDocument)
			throws SolrServerException, IOException {
		httpSolrServer.add(solrInputDocument);
		return httpSolrServer.commit();
	}

	public UpdateResponse addBean(BasicDocument document) throws IOException,
			SolrServerException {
		httpSolrServer.addBean(document);
		return httpSolrServer.commit();
	}

	public QueryResponse query(SolrQuery solrQuery) throws SolrServerException {
		return httpSolrServer.query(solrQuery);
	}

	public UpdateResponse deleteById(String id) throws SolrServerException,
			IOException {
		httpSolrServer.deleteById(id);
		return httpSolrServer.commit();
	}
}

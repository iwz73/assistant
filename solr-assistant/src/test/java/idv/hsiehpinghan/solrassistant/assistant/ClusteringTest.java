package idv.hsiehpinghan.solrassistant.assistant;

import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ClusteringTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void clusteringRequestHandlerTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setRequestHandler("/clustering");
		solrQuery.setQuery("name:drive OR features:drive");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = (SolrDocumentList) response.getResponse().get("response");
		Assert.assertTrue(solrDocumentList.size() > 0);
		@SuppressWarnings("unchecked")
		ArrayList<SimpleOrderedMap<?>> clusters = (ArrayList<SimpleOrderedMap<?>>) response.getResponse()
				.get("clusters");
		for (int i = 0, size = clusters.size(); i < size; ++i) {
			System.err.println("------------------cluster(" + i + ")---------------------");
			SimpleOrderedMap<?> cluster = clusters.get(i);
			for (int j = 0, sz = cluster.size(); j < sz; ++j) {
				String clusterName = cluster.getName(j);
				if ("labels".equals(clusterName)) {
					@SuppressWarnings("unchecked")
					ArrayList<String> clusterVals = (ArrayList<String>) cluster.getVal(j);
					System.err.println("labels:" + clusterVals);
				} else if ("score".equals(clusterName)) {
					Double clusterVals = (Double) cluster.getVal(j);
					System.err.println("score:" + clusterVals);
				} else if ("docs".equals(clusterName)) {
					@SuppressWarnings("unchecked")
					ArrayList<String> clusterVals = (ArrayList<String>) cluster.getVal(j);
					System.err.println("docs:" + clusterVals);
				} else if ("other-topics".equals(clusterName)) {
					Boolean clusterVals = (Boolean) cluster.getVal(j);
					System.err.println("other-topics:" + clusterVals);
				} else {
					throw new RuntimeException("cluster name(" + clusterName + ") not implements !!!");
				}
			}
		}
		Assert.assertEquals(clusters.size(), 2);
	}

}

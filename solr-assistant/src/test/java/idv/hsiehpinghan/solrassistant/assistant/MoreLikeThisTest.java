package idv.hsiehpinghan.solrassistant.assistant;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MoreLikeThisTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	/**
	 * It is important to note that the More Like This functionality requires
	 * that any fields used for statistical analysis (specified in the mlt.fl
	 * parameter) either have termVectors="true" or stored="true" set in the
	 * schema.xml.
	 * 
	 * @throws SolrServerException
	 */
	@Test
	public void moreLikeThisHandlerTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setRequestHandler("/mlt");
		solrQuery.setQuery("features:text");
		solrQuery.setParam("mlt.fl", "features");
		solrQuery.setParam("mlt.boost", "true");
		solrQuery.setParam("mlt.interestingTerms", "details");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = (SolrDocumentList) response.getResponse().get("response");
		Assert.assertTrue(solrDocumentList.size() > 0);
		@SuppressWarnings("unchecked")
		NamedList<Float> interestingTerms = (NamedList<Float>) response.getResponse().get("interestingTerms");
		for (int i = 0, size = interestingTerms.size(); i < size; ++i) {
			String name = interestingTerms.getName(i);
			Float val = (Float) interestingTerms.getVal(i);
			Assert.assertEquals(name, "features:and");
			Assert.assertEquals(val.toString(), "1.0");
		}
		Assert.assertEquals(interestingTerms.size(), 1);
	}

	/**
	 * It is important to note that the More Like This functionality requires
	 * that any fields used for statistical analysis (specified in the mlt.fl
	 * parameter) either have termVectors="true" or stored="true" set in the
	 * schema.xml.
	 * 
	 * @throws SolrServerException
	 */
	@Test
	public void mltTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setRequestHandler("/select");
		solrQuery.setQuery("features:text");
		solrQuery.setParam("mlt", "on");
		solrQuery.setParam("mlt.fl", "features");
		solrQuery.setParam("mlt.boost", "true");
		QueryResponse response = solrAssistant.query(solrQuery);
		SolrDocumentList solrDocumentList = (SolrDocumentList) response.getResponse().get("response");
		Assert.assertTrue(solrDocumentList.size() > 0);
	}
}

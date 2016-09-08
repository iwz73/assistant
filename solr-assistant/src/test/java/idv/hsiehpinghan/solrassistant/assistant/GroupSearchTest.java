package idv.hsiehpinghan.solrassistant.assistant;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.solrassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class GroupSearchTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SolrAssistant solrAssistant;

	@Test
	public void groupSearchTest() throws Exception {
		groupFieldTest();
		groupMainTest();
		groupFormatTest();
		groupFuncTest();
		groupQueryTest();
	}
	
	private void groupQueryTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setParam("group", "true");
		solrQuery.add("group.query", "name:ipod");
		solrQuery.add("group.query", "card");
		solrQuery.setParam("group.limit", "3");
		QueryResponse response = solrAssistant.query(solrQuery);
		System.err.println("<< groupQueryTest >>");
		GroupResponse groupResponse = response.getGroupResponse();
		List<GroupCommand> groupCommands = groupResponse.getValues();
		int i = 0;
		for (GroupCommand groupCommand : groupCommands) {
			for (Group group : groupCommand.getValues()) {
				SolrDocumentList solrDocumentList = group.getResult();
				for (SolrDocument doc : solrDocumentList) {
					System.err.println(group.getGroupValue() + " / " + doc);
					++i;
				}
			}
		}
		Assert.assertTrue(i > 0);
	}
	
	private void groupFuncTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setParam("group", "true");
		solrQuery.setParam("group.func", "map(map(map(popularity,1,5,1),6,10,2),11,100,3)");
		solrQuery.setParam("group.limit", "3");
		QueryResponse response = solrAssistant.query(solrQuery);
		System.err.println("<< groupFuncTest >>");
		GroupResponse groupResponse = response.getGroupResponse();
		List<GroupCommand> groupCommands = groupResponse.getValues();
		int i = 0;
		for (GroupCommand groupCommand : groupCommands) {
			for (Group group : groupCommand.getValues()) {
				SolrDocumentList solrDocumentList = group.getResult();
				for (SolrDocument doc : solrDocumentList) {
					System.err.println(group.getGroupValue() + " / " + doc);
					++i;
				}
			}
		}
		Assert.assertTrue(i > 0);
	}
	
	private void groupFormatTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("ipod");
		solrQuery.setParam("group", "true");
		solrQuery.setParam("group.field", "manu_id_s");
		solrQuery.setParam("group.format", "simple");
		solrQuery.setParam("group.limit", "3");
		QueryResponse response = solrAssistant.query(solrQuery);
		System.err.println("<< groupMainTest >>");
		GroupResponse groupResponse = response.getGroupResponse();
		List<GroupCommand> groupCommands = groupResponse.getValues();
		int i = 0;
		for (GroupCommand groupCommand : groupCommands) {
			for (Group group : groupCommand.getValues()) {
				SolrDocumentList solrDocumentList = group.getResult();
				for (SolrDocument doc : solrDocumentList) {
					System.err.println(group.getGroupValue() + " / " + doc);
					++i;
				}
			}
		}
		Assert.assertTrue(i > 0);
	}

	private void groupMainTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("ipod");
		solrQuery.setParam("group", "true");
		solrQuery.setParam("group.field", "manu_id_s");
		solrQuery.setParam("group.limit", "3");
		solrQuery.setParam("group.main", "true");
		QueryResponse response = solrAssistant.query(solrQuery);
		System.err.println("<< groupMainTest >>");
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument doc : solrDocumentList) {
			System.err.println(doc);
		}
		Assert.assertTrue(solrDocumentList.size() > 0);
	}

	private void groupFieldTest() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("ipod");
		solrQuery.setParam("group", "true");
		solrQuery.setParam("group.limit", "3");
		solrQuery.add("group.field", "manu_id_s");
		solrQuery.add("group.field", "popularity");
		QueryResponse response = solrAssistant.query(solrQuery);
		System.err.println("<< groupedSearchTest >>");
		GroupResponse groupResponse = response.getGroupResponse();
		List<GroupCommand> groupCommands = groupResponse.getValues();
		int i = 0;
		for (GroupCommand groupCommand : groupCommands) {
			for (Group group : groupCommand.getValues()) {
				SolrDocumentList solrDocumentList = group.getResult();
				for (SolrDocument doc : solrDocumentList) {
					System.err.println(group.getGroupValue() + " / " + doc);
					++i;
				}
			}
		}
		Assert.assertTrue(i > 0);
	}

}

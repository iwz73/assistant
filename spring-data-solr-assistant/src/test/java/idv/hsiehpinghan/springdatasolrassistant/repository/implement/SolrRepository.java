package idv.hsiehpinghan.springdatasolrassistant.repository.implement;

import idv.hsiehpinghan.springdatasolrassistant.entity.SolrEntity;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.DefaultQueryParser;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Component;

@Component
public class SolrRepository {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private SolrTemplate solrTemplate;

	public SolrResultPage<SolrEntity> query(String q, String fq, String sort,
			String fl, String df, Pageable pageable) throws SolrServerException {
		SolrQuery solrQuery = generateSolrQuery(q, fq, sort, fl, df, pageable);
		QueryResponse response = solrTemplate.getSolrServer().query(solrQuery);
		List<SolrEntity> entities = solrTemplate.convertQueryResponseToBeans(
				response, SolrEntity.class);

		return new SolrResultPage<SolrEntity>(entities, pageable, response
				.getResults().getNumFound(), 100f);
	}

	private SolrQuery generateSolrQuery(String q, String fq, String sort,
			String fl, String df, Pageable pageable) {
		SimpleQuery simpleQuery = generateSimpleQuery();
		SolrQuery solrQuery = new DefaultQueryParser()
				.constructSolrQuery(simpleQuery);
		if (null != q) {
			solrQuery.set("q", q);
		}
		if (null != fq) {
			solrQuery.set("fq", fq);
		}
		if (null != sort) {
			solrQuery.set("sort", sort);
		}
		if (null != fl) {
			solrQuery.set("fl", fl);
		}
		if (null != df) {
			solrQuery.set("df", df);
		}
		solrQuery.setStart(pageable.getOffset());
		solrQuery.setRows(pageable.getPageSize());
		return solrQuery;
	}

	private SimpleQuery generateSimpleQuery() {
		Criteria criteria = new Criteria("name").contains("iPod");
		SimpleQuery simpleQuery = new SimpleQuery(criteria);
		simpleQuery.addProjectionOnField("*");
		simpleQuery.addProjectionOnField("score");
		return simpleQuery;
	}
}
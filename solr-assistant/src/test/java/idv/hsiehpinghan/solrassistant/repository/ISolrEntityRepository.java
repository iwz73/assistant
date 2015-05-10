package idv.hsiehpinghan.solrassistant.repository;

import idv.hsiehpinghan.solrassistant.entity.SolrEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ISolrEntityRepository extends
		SolrCrudRepository<SolrEntity, String> {
	@Query("?0")
	Page<SolrEntity> query(String criteria, Pageable pageable);

	@Query(value = "?0")
	@Facet(fields = { "name" })
	FacetPage<SolrEntity> findAllFacetOnName(String criteria, Pageable page);
}

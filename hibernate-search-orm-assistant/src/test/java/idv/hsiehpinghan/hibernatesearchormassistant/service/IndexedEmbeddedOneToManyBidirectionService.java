package idv.hsiehpinghan.hibernatesearchormassistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedOneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.IndexedEmbeddedOneToManyBidirectionRepository;

@Service
@Transactional
public class IndexedEmbeddedOneToManyBidirectionService {
	@Autowired
	private IndexedEmbeddedOneToManyBidirectionRepository repository;

	public void save(IndexedEmbeddedOneToManyBidirectionOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IndexedEmbeddedOneToManyBidirectionOneEntity findOne(int id) {
		IndexedEmbeddedOneToManyBidirectionOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<IndexedEmbeddedOneToManyBidirectionOneEntity> luceneQuery(org.apache.lucene.search.Query query) {
		return repository.luceneQuery(query);
	}
}

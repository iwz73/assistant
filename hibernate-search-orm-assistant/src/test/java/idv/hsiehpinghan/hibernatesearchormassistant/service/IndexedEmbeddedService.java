package idv.hsiehpinghan.hibernatesearchormassistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.IndexedEmbeddedRepository;

@Service
@Transactional
public class IndexedEmbeddedService {
	@Autowired
	private IndexedEmbeddedRepository repository;

	public void save(IndexedEmbeddedEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IndexedEmbeddedEntity findOne(int id) {
		return repository.findOne(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<IndexedEmbeddedEntity> luceneQuery(org.apache.lucene.search.Query query) {
		return repository.luceneQuery(query);
	}
}

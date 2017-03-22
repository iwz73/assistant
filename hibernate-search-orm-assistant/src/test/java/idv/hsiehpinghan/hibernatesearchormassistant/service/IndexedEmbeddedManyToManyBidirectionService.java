package idv.hsiehpinghan.hibernatesearchormassistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedManyToManyBidirectionToEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.IndexedEmbeddedManyToManyBidirectionRepository;

@Service
@Transactional
public class IndexedEmbeddedManyToManyBidirectionService {
	@Autowired
	private IndexedEmbeddedManyToManyBidirectionRepository repository;

	public void saveOrUpdate(IndexedEmbeddedManyToManyBidirectionFromEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public IndexedEmbeddedManyToManyBidirectionFromEntity findOne(int id) {
		IndexedEmbeddedManyToManyBidirectionFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		for (IndexedEmbeddedManyToManyBidirectionToEntity to : entity.getTos()) {
			to.getFroms().size();
		}
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<IndexedEmbeddedManyToManyBidirectionFromEntity> luceneQuery(org.apache.lucene.search.Query query) {
		return repository.luceneQuery(query);
	}

}

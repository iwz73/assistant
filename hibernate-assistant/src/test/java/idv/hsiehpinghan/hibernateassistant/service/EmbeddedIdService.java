package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.repository.EmbeddedIdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmbeddedIdService {
	@Autowired
	private EmbeddedIdRepository repository;

	public void save(EmbeddedIdContainerEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public EmbeddedIdContainerEntity findOne(EmbeddedIdEmbeddableEntity id) {
		EmbeddedIdContainerEntity entity = repository.findOne(id);
		return entity;
	}

	public int deleteAll() {
		return repository.deleteAll();
	}
}

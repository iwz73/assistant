package idv.hsiehpinghan.hibernatesearchormassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdEmbeddableEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.EmbeddedIdRepository;

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

}

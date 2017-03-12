package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdOneIdEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyDerivedEmbeddedIdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyDerivedEmbeddedIdService {
	@Autowired
	private OneToManyDerivedEmbeddedIdRepository repository;

	public void save(OneToManyDerivedEmbeddedIdOneEntity entity) {
		repository.save(entity);
	}

	public void delete(OneToManyDerivedEmbeddedIdOneEntity entity) {
		repository.delete(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public OneToManyDerivedEmbeddedIdOneEntity findOne(
			OneToManyDerivedEmbeddedIdOneIdEntity id) {
		OneToManyDerivedEmbeddedIdOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}

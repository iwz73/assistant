package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapEmbeddableOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyMapEmbeddableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyMapEmbeddableService {
	@Autowired
	private OneToManyMapEmbeddableRepository repository;

	public void save(OneToManyMapEmbeddableOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public OneToManyMapEmbeddableOneEntity findOne(int id) {
		OneToManyMapEmbeddableOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}

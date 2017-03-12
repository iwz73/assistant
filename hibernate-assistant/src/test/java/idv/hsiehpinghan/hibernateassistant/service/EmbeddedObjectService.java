package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectContainerEntity;
import idv.hsiehpinghan.hibernateassistant.repository.EmbeddedObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmbeddedObjectService {
	@Autowired
	private EmbeddedObjectRepository repository;

	public void save(EmbeddedObjectContainerEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public EmbeddedObjectContainerEntity findOne(int id) {
		EmbeddedObjectContainerEntity entity = repository.findOne(id);
		return entity;
	}
}

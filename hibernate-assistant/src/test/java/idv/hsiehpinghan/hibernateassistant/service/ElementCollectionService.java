package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ElementCollectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementCollectionService {
	@Autowired
	private ElementCollectionRepository repository;

	public void save(ElementCollectionEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ElementCollectionEntity findOne(int id) {
		ElementCollectionEntity entity = repository.findOne(id);
		entity.getElements().size();
		return entity;
	}
}

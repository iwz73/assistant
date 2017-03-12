package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionTableContainerEntity2;
import idv.hsiehpinghan.hibernateassistant.repository.ElementCollectionRepository2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementCollectionService2 {
	@Autowired
	private ElementCollectionRepository2 repository;

	public void save(ElementCollectionTableContainerEntity2 entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ElementCollectionTableContainerEntity2 findOne(int id) {
		ElementCollectionTableContainerEntity2 entity = repository.findOne(id);
		entity.getElements().size();
		return entity;
	}
}

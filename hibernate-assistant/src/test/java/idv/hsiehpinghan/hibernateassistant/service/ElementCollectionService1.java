package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionContainerEntity1;
import idv.hsiehpinghan.hibernateassistant.repository.ElementCollectionRepository1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementCollectionService1 {
	@Autowired
	private ElementCollectionRepository1 repository;

	public void save(ElementCollectionContainerEntity1 entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ElementCollectionContainerEntity1 findOne(int id) {
		ElementCollectionContainerEntity1 entity = repository.findOne(id);
		entity.getElements().size();
		return entity;
	}
}

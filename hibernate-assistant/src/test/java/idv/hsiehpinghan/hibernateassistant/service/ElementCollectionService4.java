package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEnumerationMapEntity4;
import idv.hsiehpinghan.hibernateassistant.repository.ElementCollectionRepository4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementCollectionService4 {
	@Autowired
	private ElementCollectionRepository4 repository;

	public void save(ElementCollectionEnumerationMapEntity4 entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ElementCollectionEnumerationMapEntity4 findOne(int id) {
		ElementCollectionEnumerationMapEntity4 entity = repository.findOne(id);
		entity.getMap().size();
		return entity;
	}
}

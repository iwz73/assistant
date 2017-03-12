package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionStringMapEntity3;
import idv.hsiehpinghan.hibernateassistant.repository.ElementCollectionRepository3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementCollectionService3 {
	@Autowired
	private ElementCollectionRepository3 repository;

	public void save(ElementCollectionStringMapEntity3 entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ElementCollectionStringMapEntity3 findOne(int id) {
		ElementCollectionStringMapEntity3 entity = repository.findOne(id);
		entity.getMap().size();
		return entity;
	}
}

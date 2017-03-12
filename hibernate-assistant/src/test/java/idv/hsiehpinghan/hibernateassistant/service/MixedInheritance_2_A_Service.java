package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.MixedInheritance_2_A_Entity;
import idv.hsiehpinghan.hibernateassistant.repository.MixedInheritance_2_A_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MixedInheritance_2_A_Service {
	@Autowired
	private MixedInheritance_2_A_Repository repository;

	public void save(MixedInheritance_2_A_Entity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MixedInheritance_2_A_Entity findOne(int id) {
		MixedInheritance_2_A_Entity entity = repository.findOne(id);
		return entity;
	}
}

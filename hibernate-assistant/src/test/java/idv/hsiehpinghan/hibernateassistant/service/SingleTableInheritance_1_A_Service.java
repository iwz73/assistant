package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.SingleTableInheritance_1_A_Entity;
import idv.hsiehpinghan.hibernateassistant.repository.SingleTableInheritance_1_A_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SingleTableInheritance_1_A_Service {
	@Autowired
	private SingleTableInheritance_1_A_Repository repository;

	public void save(SingleTableInheritance_1_A_Entity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public SingleTableInheritance_1_A_Entity findOne(int id) {
		SingleTableInheritance_1_A_Entity entity = repository.findOne(id);
		return entity;
	}
}

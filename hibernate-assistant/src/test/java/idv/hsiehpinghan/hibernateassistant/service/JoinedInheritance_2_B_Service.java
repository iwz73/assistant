package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.JoinedInheritance_2_B_Entity;
import idv.hsiehpinghan.hibernateassistant.repository.JoinedInheritance_2_B_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JoinedInheritance_2_B_Service {
	@Autowired
	private JoinedInheritance_2_B_Repository repository;

	public void save(JoinedInheritance_2_B_Entity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public JoinedInheritance_2_B_Entity findOne(int id) {
		JoinedInheritance_2_B_Entity entity = repository.findOne(id);
		return entity;
	}
}

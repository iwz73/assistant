package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyUnidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyUnidirectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyUnidirectionService {
	@Autowired
	private OneToManyUnidirectionRepository repository;

	public void save(OneToManyUnidirectionOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToManyUnidirectionOneEntity findOne(int id) {
		OneToManyUnidirectionOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}

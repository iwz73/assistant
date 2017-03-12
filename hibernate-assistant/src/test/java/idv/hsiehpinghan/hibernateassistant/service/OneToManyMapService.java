package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyMapRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyMapService {
	@Autowired
	private OneToManyMapRepository repository;

	public void save(OneToManyMapOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public OneToManyMapOneEntity findOne(int id) {
		OneToManyMapOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}

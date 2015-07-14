package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyListOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyListService {
	@Autowired
	private OneToManyListRepository repository;

	public void save(OneToManyListOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToManyListOneEntity findOne(int id) {
		OneToManyListOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}

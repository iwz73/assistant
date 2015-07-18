package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneMapsIdMainEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOneMapsIdMainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneMapsIdMainService {
	@Autowired
	private OneToOneMapsIdMainRepository repository;

	public void save(OneToOneMapsIdMainEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToOneMapsIdMainEntity findOne(int id) {
		OneToOneMapsIdMainEntity entity = repository.findOne(id);
		return entity;
	}
}

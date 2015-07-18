package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneDerivedSingleKeyMainEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOneDerivedSingleKeyMainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneDerivedSingleKeyMainService {
	@Autowired
	private OneToOneDerivedSingleKeyMainRepository repository;

	public void save(OneToOneDerivedSingleKeyMainEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToOneDerivedSingleKeyMainEntity findOne(int id) {
		OneToOneDerivedSingleKeyMainEntity entity = repository.findOne(id);
		return entity;
	}
}

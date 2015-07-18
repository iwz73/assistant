package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneDerivedSingleKeyDerivedEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOneDerivedSingleKeyDerivedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneDerivedSingleKeyDerivedService {
	@Autowired
	private OneToOneDerivedSingleKeyDerivedRepository repository;

	public void save(OneToOneDerivedSingleKeyDerivedEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToOneDerivedSingleKeyDerivedEntity findOne(int i) {
		OneToOneDerivedSingleKeyDerivedEntity entity = repository.findOne(i);
		return entity;
	}
}

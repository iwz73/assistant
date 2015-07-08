package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOneBidirectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneBidirectionService {
	@Autowired
	private OneToOneBidirectionRepository repository;

	public void save(OneToOneBidirectionFromEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToOneBidirectionFromEntity findOne(int id) {
		OneToOneBidirectionFromEntity entity = repository.findOne(id);
		return entity;
	}

}

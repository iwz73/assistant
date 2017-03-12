package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOneUnidirectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneUnidirectionService {
	@Autowired
	private OneToOneUnidirectionRepository repository;

	public void save(OneToOneUnidirectionFromEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public OneToOneUnidirectionFromEntity findOne(int id) {
		OneToOneUnidirectionFromEntity entity = repository.findOne(id);
		return entity;
	}

}

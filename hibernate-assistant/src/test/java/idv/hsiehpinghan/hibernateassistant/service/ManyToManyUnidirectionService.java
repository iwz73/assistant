package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToManyUnidirectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyUnidirectionService {
	@Autowired
	private ManyToManyUnidirectionRepository repository;

	public void saveOrUpdate(ManyToManyUnidirectionFromEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ManyToManyUnidirectionFromEntity findOne(int id) {
		ManyToManyUnidirectionFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		return entity;
	}

}

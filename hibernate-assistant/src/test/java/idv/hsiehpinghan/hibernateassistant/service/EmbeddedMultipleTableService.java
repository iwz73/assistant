package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedMultipleTableContainerEntity;
import idv.hsiehpinghan.hibernateassistant.repository.EmbeddedMultipleTableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmbeddedMultipleTableService {
	@Autowired
	private EmbeddedMultipleTableRepository repository;

	public void save(EmbeddedMultipleTableContainerEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public EmbeddedMultipleTableContainerEntity findOne(int id) {
		EmbeddedMultipleTableContainerEntity entity = repository.findOne(id);
		return entity;
	}

}

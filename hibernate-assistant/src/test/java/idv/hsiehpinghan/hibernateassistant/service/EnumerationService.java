package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.EnumerationEntity;
import idv.hsiehpinghan.hibernateassistant.repository.EnumerationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnumerationService {
	@Autowired
	private EnumerationRepository repository;

	public void save(EnumerationEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public EnumerationEntity findOne(int id) {
		EnumerationEntity entity = repository.findOne(id);
		return entity;
	}

}

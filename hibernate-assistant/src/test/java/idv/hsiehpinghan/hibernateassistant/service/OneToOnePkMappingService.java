package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingFromEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOnePkMappingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOnePkMappingService {
	@Autowired
	private OneToOnePkMappingRepository repository;

	public void save(OneToOnePkMappingFromEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToOnePkMappingFromEntity findOne(int id) {
		OneToOnePkMappingFromEntity entity = repository.findOne(id);
		return entity;
	}

}

package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.hibernateassistant.repository.BasicTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTypeService {
	@Autowired
	private BasicTypeRepository repository;

	public void save(BasicTypeEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public BasicTypeEntity findOne(int id) {
		BasicTypeEntity entity = repository.findOne(id);
		return entity;
	}

}

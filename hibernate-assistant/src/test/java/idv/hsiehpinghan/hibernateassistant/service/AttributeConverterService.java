package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.AttributeConverterEntity;
import idv.hsiehpinghan.hibernateassistant.repository.AttributeConverterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AttributeConverterService {
	@Autowired
	private AttributeConverterRepository repository;

	public void save(AttributeConverterEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public AttributeConverterEntity findOne(int id) {
		AttributeConverterEntity entity = repository.findOne(id);
		return entity;
	}
}

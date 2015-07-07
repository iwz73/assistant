package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.SequenceGeneratorEntity;
import idv.hsiehpinghan.hibernateassistant.repository.SequenceGeneratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SequenceGeneratorService {
	@Autowired
	private SequenceGeneratorRepository repository;

	public void save(SequenceGeneratorEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public SequenceGeneratorEntity findOne(int id) {
		SequenceGeneratorEntity entity = repository.findOne(id);
		return entity;
	}

}

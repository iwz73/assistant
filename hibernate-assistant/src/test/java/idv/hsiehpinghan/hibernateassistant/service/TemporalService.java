package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.TemporalEntity;
import idv.hsiehpinghan.hibernateassistant.repository.TemporalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TemporalService {
	@Autowired
	private TemporalRepository repository;

	public void save(TemporalEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public TemporalEntity findOne(int id) {
		TemporalEntity entity = repository.findOne(id);
		return entity;
	}

}

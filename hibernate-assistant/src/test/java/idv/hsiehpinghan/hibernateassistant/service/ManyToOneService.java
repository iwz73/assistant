package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneManyEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToOneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToOneService {
	@Autowired
	private ManyToOneRepository repository;

	public void save(ManyToOneManyEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToOneManyEntity findOne(int id) {
		ManyToOneManyEntity entity = repository.findOne(id);
		return entity;
	}

}

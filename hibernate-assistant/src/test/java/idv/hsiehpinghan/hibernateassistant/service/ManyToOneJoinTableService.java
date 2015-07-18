package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinTableManyEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToOneJoinTableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToOneJoinTableService {
	@Autowired
	private ManyToOneJoinTableRepository repository;

	public void save(ManyToOneJoinTableManyEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToOneJoinTableManyEntity findOne(int id) {
		ManyToOneJoinTableManyEntity entity = repository.findOne(id);
		return entity;
	}

}

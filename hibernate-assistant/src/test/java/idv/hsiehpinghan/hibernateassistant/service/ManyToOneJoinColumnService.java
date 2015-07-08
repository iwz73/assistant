package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnManyEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToOneJoinColumnRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToOneJoinColumnService {
	@Autowired
	private ManyToOneJoinColumnRepository repository;

	public void save(ManyToOneJoinColumnManyEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToOneJoinColumnManyEntity findOne(int id) {
		ManyToOneJoinColumnManyEntity entity = repository.findOne(id);
		return entity;
	}

}

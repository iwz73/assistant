package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.MultipleTableEntity;
import idv.hsiehpinghan.hibernateassistant.repository.MultipleTableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MultipleTableService {
	@Autowired
	private MultipleTableRepository repository;

	public void save(MultipleTableEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MultipleTableEntity findOne(int id) {
		MultipleTableEntity entity = repository.findOne(id);
		return entity;
	}

}

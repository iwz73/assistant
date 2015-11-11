package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.BatchEntity;
import idv.hsiehpinghan.hibernateassistant.repository.BatchRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BatchService {
	@Autowired
	private BatchRepository repository;

	public int batchSave(Collection<BatchEntity> entities, int batchSize) {
		return repository.batchSave(entities, batchSize);
	}

	public int batchUpdate(String string, int batchSize) {
		return repository.batchUpdate(string, batchSize);
	}

	public int deleteAll() {
		return repository.deleteAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BatchEntity> findAll() {
		return repository.findAll();
	}

}

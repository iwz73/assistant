package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.TableGeneratorEntity;
import idv.hsiehpinghan.hibernateassistant.repository.TableGeneratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TableGeneratorService {
	@Autowired
	private TableGeneratorRepository repository;

	public void save(TableGeneratorEntity entity) {
		repository.save(entity);
	}

	public void dropTable() {
		repository.dropTable();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TableGeneratorEntity findOne(int id) {
		TableGeneratorEntity entity = repository.findOne(id);
		return entity;
	}

}

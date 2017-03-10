package idv.hsiehpinghan.hibernatesearchormassistant.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.BasicTypeRepository;

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
		return repository.findOne(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String findClobAsString(int id) throws SQLException, IOException {
		return repository.findClobAsString(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String findBlobAsString(int id) throws SQLException, IOException {
		String s = repository.findBlobAsString(id);
		return s;
	}

}
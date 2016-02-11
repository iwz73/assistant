package idv.hsiehpinghan.springjdbcassistant.service;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springjdbcassistant.repository.BasicTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTypeService {
	@Autowired
	private BasicTypeRepository repository;

	public int insertByPreparedStatementCreator(BasicTypeEntity entity) {
		return repository.insertByPreparedStatementCreator(entity);
	}

	public int updateByPreparedStatementCreator(BasicTypeEntity entity) {
		return repository.updateByPreparedStatementCreator(entity);
	}

	public void deleteByPreparedStatementCreator(long id) {
		repository.deleteByPreparedStatementCreator(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BasicTypeEntity queryForObjectByRowMapper(long id) {
		return repository.queryForObjectByRowMapper(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean exists(long id) {
		return repository.exists(id);
	}
}

package idv.hsiehpinghan.springbatchassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbatchassistant.entity.JdbcEntity;
import idv.hsiehpinghan.springbatchassistant.repository.JdbcRepository;

@Service
@Transactional("transactionManager")
public class JdbcService {
	@Autowired
	private JdbcRepository repository;

	public int insertByPreparedStatementCreator(JdbcEntity entity) {
		return repository.insertByPreparedStatementCreator(entity);
	}

	public int updateByPreparedStatementCreator(JdbcEntity entity) {
		return repository.updateByPreparedStatementCreator(entity);
	}

	public void deleteByPreparedStatementCreator(long id) {
		repository.deleteByPreparedStatementCreator(id);
	}

	@Transactional(value = "transactionManager", readOnly = true, propagation = Propagation.SUPPORTS)
	public JdbcEntity queryForObjectByRowMapper(long id) {
		return repository.queryForObjectByRowMapper(id);
	}

	@Transactional(value = "transactionManager", readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean exists(long id) {
		return repository.exists(id);
	}
}

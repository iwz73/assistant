package idv.hsiehpinghan.springjdbcassistant.service;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springjdbcassistant.repository.BasicTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTypeService {
	@Autowired
	private BasicTypeRepository repository;

	public int insertByAbstractLobCreatingPreparedStatementCallback(
			BasicTypeEntity entity, final long clobContentLength,
			final long blobContentLength) {
		return repository.insertByAbstractLobCreatingPreparedStatementCallback(
				entity, clobContentLength, blobContentLength);
	}

	// public int insertByPreparedStatement(BasicTypeEntity entity) {
	// return repository.insertByPreparedStatement(entity);
	// }
	//
	// @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	// public BasicTypeEntity queryForObjectByRowMapper(long id) {
	// return repository.queryForObjectByRowMapper(id);
	// }
}

package idv.hsiehpinghan.hibernatesearchormassistant.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.RamDirectoryProviderEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.RamDirectoryProviderRepository;

@Service
@Transactional
public class RamDirectoryProviderService {
	@Autowired
	private RamDirectoryProviderRepository repository;

	public void save(RamDirectoryProviderEntity entity) {
		repository.save(entity);
	}

	public void saveOrUpdate(RamDirectoryProviderEntity entity) {
		repository.saveOrUpdate(entity);
	}

	public void remove(RamDirectoryProviderEntity entity) {
		repository.remove(entity);
	}

	public int reindexAll() {
		return repository.reindexAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RamDirectoryProviderEntity findOne(int id) {
		return repository.findOne(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String findClobAsString(int id) throws SQLException, IOException {
		return repository.findClobAsString(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public String findBlobAsString(int id) throws SQLException, IOException {
		String s = repository.findBlobAsString(id);
		return s;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RamDirectoryProviderEntity> luceneQuery(org.apache.lucene.search.Query query) {
		return repository.luceneQuery(query);
	}
}

package idv.hsiehpinghan.springbatchassistant.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbatchassistant.entity.HibernateEntity;
import idv.hsiehpinghan.springbatchassistant.repository.HibernateRepository;

@Service
@Transactional(value = "hibernateTransactionManager")
public class HibernateService {
	@Autowired
	private HibernateRepository repository;

	public void save(HibernateEntity entity) {
		repository.save(entity);
	}

	@Transactional(value = "hibernateTransactionManager", readOnly = true, propagation = Propagation.REQUIRED)
	public HibernateEntity findOne(int id) {
		return repository.findOne(id);
	}

	@Transactional(value = "hibernateTransactionManager", readOnly = true, propagation = Propagation.REQUIRED)
	public Long count() {
		return repository.count();
	}

	@Transactional(value = "hibernateTransactionManager", readOnly = true, propagation = Propagation.REQUIRED)
	public String findClobAsString(int id) throws SQLException, IOException {
		return repository.findClobAsString(id);
	}

	@Transactional(value = "hibernateTransactionManager", readOnly = true, propagation = Propagation.REQUIRED)
	public String findBlobAsString(int id) throws SQLException, IOException {
		String s = repository.findBlobAsString(id);
		return s;
	}

}

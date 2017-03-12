package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ClearEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ClearRepository;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClearService {
	private Statistics statistics;
	@Autowired
	private ClearRepository repository;

	public Long save(ClearEntity entity) {
		return repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ClearEntity findOne(long id) {
		ClearEntity entity = repository.findOne(id);
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ClearEntity clearAndGet(long id) {
		clearStatistics();
		ClearEntity entity = repository.findOne(id);
		entity.setString("clearAndGet");
		repository.clear();
		return repository.findOne(id);
	}

	public Statistics getStatistics() {
		return statistics;
	}

	private void clearStatistics() {
		statistics = repository.getSessionFactory().getStatistics();
		statistics.clear();
	}

}

package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.EvictEntity;
import idv.hsiehpinghan.hibernateassistant.repository.EvictRepository;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EvictService {
	private Statistics statistics;
	@Autowired
	private EvictRepository repository;

	public Long save(EvictEntity entity) {
		return repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public EvictEntity findOne(long id) {
		EvictEntity entity = repository.findOne(id);
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public EvictEntity evictAndGet(long id) {
		clearStatistics();
		EvictEntity entity = repository.findOne(id);
		entity.setString("evictAndGet");
		repository.evict(entity);
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

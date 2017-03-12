package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ReadOnlyEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ReadOnlyRepository;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReadOnlyService {
	private Statistics statistics;
	@Autowired
	private ReadOnlyRepository repository;

	public Long save(ReadOnlyEntity entity) {
		return repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReadOnlyEntity findOne(long id) {
		ReadOnlyEntity entity = repository.findOne(id);
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReadOnlyEntity setReadOnlyAndGet(long id) {
		clearStatistics();
		ReadOnlyEntity entity = repository.findOne(id);
		entity.setString("clearAndGet");
		repository.setReadOnly(entity);
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

package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.LockOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.LockRepository;

import org.hibernate.LockOptions;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@Service
@Transactional
public class LockService {
	private Statistics statistics;
	@Autowired
	private LockRepository repository;

	public Long save(LockOneEntity entity) {
		return repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public LockOneEntity findOne(long id) {
		LockOneEntity entity = repository.findOne(id);
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void lockModeNone(LockOneEntity entity) {
		clearStatistics();
		repository.lock(entity, LockOptions.NONE);
		Assert.assertEquals(entity.getMany().size(), 3);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void lockModeRead(LockOneEntity entity) {
		clearStatistics();
		repository.lock(entity, LockOptions.READ);
	}

	public Statistics getStatistics() {
		return statistics;
	}

	private void clearStatistics() {
		statistics = repository.getSessionFactory().getStatistics();
		statistics.clear();
	}

}

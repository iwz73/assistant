package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.FlushModeEntity;
import idv.hsiehpinghan.hibernateassistant.repository.FlushModeRepository;

import org.hibernate.FlushMode;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@Service
@Transactional
public class FlushModeService {
	private Statistics statistics;
	@Autowired
	private FlushModeRepository repository;

	public Long save(FlushModeEntity entity) {
		return repository.save(entity);
	}

	public FlushModeEntity flushModeAuto(long id) {
		clearStatistics();
		FlushModeEntity entity = repository.findOne(id);
		entity.setString("flushModeAuto");
		repository.update(entity);
		repository.flush();
		Assert.assertEquals(statistics.getFlushCount(), 1);
		return entity;
	}

	public FlushModeEntity flushModeManual(long id) {
		clearStatistics();
		repository.setFlushMode(FlushMode.MANUAL);
		FlushModeEntity entity = repository.findOne(id);
		entity.setString("flushModeManual");
		repository.update(entity);
		repository.flush();
		Assert.assertEquals(statistics.getFlushCount(), 1);
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public FlushModeEntity findOne(long id) {
		FlushModeEntity entity = repository.findOne(id);
		return entity;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	private void clearStatistics() {
		statistics = repository.getSessionFactory().getStatistics();
		statistics.clear();
	}

}

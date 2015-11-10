package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.LifeCycleEntity;
import idv.hsiehpinghan.hibernateassistant.repository.LifeCycleRepository;

import java.util.List;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@Service
@Transactional
public class LifeCycleService {
	private Statistics statistics;
	@Autowired
	private LifeCycleRepository repository;

	public Long save(LifeCycleEntity entity) {
		clearStatistics();
		Long id = repository.save(entity);
		Assert.assertEquals(statistics.getEntityInsertCount(), 0);
		Assert.assertEquals(statistics.getFlushCount(), 0);
		return id;
	}

	public void update(LifeCycleEntity entity) {
		clearStatistics();
		repository.update(entity);
		Assert.assertEquals(statistics.getEntityUpdateCount(), 0);
		Assert.assertEquals(statistics.getFlushCount(), 0);
	}

	public List<LifeCycleEntity> updateAndSelect(LifeCycleEntity entity) {
		clearStatistics();
		repository.update(entity);
		Assert.assertEquals(statistics.getEntityUpdateCount(), 0);
		Assert.assertEquals(statistics.getFlushCount(), 0);
		List<LifeCycleEntity> entities = repository.findAll();
		Assert.assertEquals(statistics.getEntityUpdateCount(), 1);
		Assert.assertEquals(statistics.getQueryExecutionCount(), 1);
		Assert.assertEquals(statistics.getFlushCount(), 1);
		return entities;
	}

	public void nonUniqueObjectUpdate(LifeCycleEntity entity) {
		repository.get(entity.getId());
		repository.update(entity);
	}

	public LifeCycleEntity merge(LifeCycleEntity entity) {
		LifeCycleEntity returnEntity = repository.get(entity.getId());
		Assert.assertFalse(entity.getString().equals(returnEntity.getString()));
		return repository.merge(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public LifeCycleEntity get(long id) {
		return repository.get(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public LifeCycleEntity load(long id) {
		LifeCycleEntity entity = repository.load(id);
		return entity;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public LifeCycleEntity getTwice(long id) {
		clearStatistics();
		LifeCycleEntity entity_0 = repository.get(id);
		LifeCycleEntity entity_1 = repository.get(id);
		Assert.assertTrue(entity_0 == entity_1);
		Assert.assertEquals(statistics.getEntityLoadCount(), 1);
		return entity_0;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	private void clearStatistics() {
		statistics = repository.getSessionFactory().getStatistics();
		statistics.clear();
	}
}

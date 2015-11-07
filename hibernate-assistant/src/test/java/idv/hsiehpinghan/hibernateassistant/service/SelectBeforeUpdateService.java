package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.SelectBeforeUpdateEntity;
import idv.hsiehpinghan.hibernateassistant.repository.SelectBeforeUpdateRepository;

import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@Service
@Transactional
public class SelectBeforeUpdateService {
	private Statistics statistics;
	@Autowired
	private SelectBeforeUpdateRepository repository;

	public Long save(SelectBeforeUpdateEntity entity) {
		return (Long) repository.save(entity);
	}

	public void selectBeforeUpdateNoChange(SelectBeforeUpdateEntity entity) {
		clearStatistics();
		repository.update(entity);
		Assert.assertEquals(statistics.getEntityUpdateCount(), 0);
	}

	public void selectBeforeUpdateWithChange(SelectBeforeUpdateEntity entity) {
		clearStatistics();
		repository.update(entity);
		Assert.assertEquals(statistics.getEntityUpdateCount(), 0);
	}

	public Statistics getStatistics() {
		return statistics;
	}

	private void clearStatistics() {
		statistics = repository.getSessionFactory().getStatistics();
		statistics.clear();
	}
}

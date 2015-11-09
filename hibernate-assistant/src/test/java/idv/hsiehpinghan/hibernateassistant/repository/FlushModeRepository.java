package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.FlushModeEntity;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlushModeRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(FlushModeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public void update(FlushModeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}

	public FlushModeEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (FlushModeEntity) session.get(FlushModeEntity.class, id);
	}

	public void setFlushMode(FlushMode flushMode) {
		Session session = sessionFactory.getCurrentSession();
		session.setFlushMode(flushMode);
	}

	public void flush() {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}

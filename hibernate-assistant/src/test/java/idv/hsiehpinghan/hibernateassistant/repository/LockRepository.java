package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.LockOneEntity;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Session.LockRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LockRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(LockOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public LockOneEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (LockOneEntity) session.get(LockOneEntity.class, id);
	}

	public void lock(LockOneEntity entity, LockOptions lockOptions) {
		Session session = sessionFactory.getCurrentSession();
		LockRequest lockRequest = session.buildLockRequest(lockOptions);
		lockRequest.lock(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}

package idv.hsiehpinghan.querydsljpaassistant.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Repository
@Transactional
public abstract class CommonServiceInheritanceService {
	@Autowired
	protected SessionFactory sessionFactory;

	public void save(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
	}

	public void saveRollback(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
		throw new RuntimeException();
	}

	protected HibernateQueryFactory getHibernateQueryFactory(Session session) {
		return new HibernateQueryFactory(session);
	}
}

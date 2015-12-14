package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QBasicTypeEntity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Service
@Repository
@Transactional
public class BasicTypeService {
	private QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
	@Autowired
	private SessionFactory sessionFactory;

	public void save(BasicTypeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
	}

	public void saveRollback(BasicTypeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
		throw new RuntimeException();
	}

	public long delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).delete(qEntity)
				.where(qEntity.id.eq(id)).execute();
	}

	public long deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).delete(qEntity).execute();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public BasicTypeEntity findOne(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).selectFrom(qEntity)
				.where(qEntity.id.eq(id)).fetchOne();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BasicTypeEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).selectFrom(qEntity).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Iterable<BasicTypeEntity> findAllDescentById(String string) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).selectFrom(qEntity)
				.orderBy(qEntity.id.desc()).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countByString(String string) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).select(qEntity.count())
				.from(qEntity).where(qEntity.string.eq(string)).fetchCount();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public boolean exists(String string) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).select(qEntity.count())
				.from(qEntity).where(qEntity.string.eq(string)).fetchCount() > 0;
	}

	private HibernateQueryFactory getHibernateQueryFactory(Session session) {
		return new HibernateQueryFactory(session);
	}
}

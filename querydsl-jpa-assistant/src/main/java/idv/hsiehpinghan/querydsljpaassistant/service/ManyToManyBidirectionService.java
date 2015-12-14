package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionToEntity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

@Service
@Repository
@Transactional
public class ManyToManyBidirectionService {
	private QManyToManyBidirectionFromEntity qFromEntity = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
	private QManyToManyBidirectionToEntity qToEntity = QManyToManyBidirectionToEntity.manyToManyBidirectionToEntity;
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ManyToManyBidirectionFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public boolean exists(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).select(qFromEntity.count())
				.from(qFromEntity).where(qFromEntity.id.eq(id)).fetchCount() > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Tuple> where(Integer id, Expression<?>... columns) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).select(columns)
				.from(qFromEntity).where(qFromEntity.id.eq(id)).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Tuple> leftJoin(Integer id, Expression<?>... columns) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).select(columns)
				.from(qFromEntity).leftJoin(qFromEntity.tos, qToEntity)
				.where(qFromEntity.id.eq(id)).fetch();
	}

	private HibernateQueryFactory getHibernateQueryFactory(Session session) {
		return new HibernateQueryFactory(session);
	}
}

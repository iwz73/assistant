package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.QMixedInheritance_1_A_Entity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QMixedInheritance_2_A_Entity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QMixedInheritance_2_B_Entity;

import java.util.Collection;

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
public class MixedInheritanceService {
	// private QMixedInheritance_0_Entity qMixedInheritance_0_Entity =
	// QMixedInheritance_0_Entity.mixedInheritance_0_Entity;
	private QMixedInheritance_1_A_Entity qMixedInheritance_1_A_Entity = QMixedInheritance_1_A_Entity.mixedInheritance_1_A_Entity;
	// private QMixedInheritance_1_B_Entity qMixedInheritance_1_B_Entity =
	// QMixedInheritance_1_B_Entity.mixedInheritance_1_B_Entity;
	private QMixedInheritance_2_A_Entity qMixedInheritance_2_A_Entity = QMixedInheritance_2_A_Entity.mixedInheritance_2_A_Entity;
	private QMixedInheritance_2_B_Entity qMixedInheritance_2_B_Entity = QMixedInheritance_2_B_Entity.mixedInheritance_2_B_Entity;

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Collection<Object> entities) {
		Session session = sessionFactory.getCurrentSession();
		entities.forEach((entity) -> {
			session.persist(entity);
		});
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public <T> T findOne(int id, Class<T> clazz) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(clazz, id);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String findA1ById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session)
				.select(qMixedInheritance_1_A_Entity.a1)
				.from(qMixedInheritance_1_A_Entity)
				.where(qMixedInheritance_1_A_Entity.id.eq(id)).fetchOne();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String findA2ById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session)
				.select(qMixedInheritance_2_A_Entity.a2)
				.from(qMixedInheritance_2_A_Entity)
				.where(qMixedInheritance_2_A_Entity.id.eq(id)).fetchOne();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String findB2ById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session)
				.select(qMixedInheritance_2_B_Entity.b2)
				.from(qMixedInheritance_2_B_Entity)
				.where(qMixedInheritance_2_B_Entity.id.eq(id)).fetchOne();
	}

	private HibernateQueryFactory getHibernateQueryFactory(Session session) {
		return new HibernateQueryFactory(session);
	}
}

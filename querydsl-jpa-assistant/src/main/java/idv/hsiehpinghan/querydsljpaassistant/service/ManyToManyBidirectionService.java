package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionToEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
@Transactional
public class ManyToManyBidirectionService {
	private QManyToManyBidirectionFromEntity qFromEntity = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
	private QManyToManyBidirectionToEntity qToEntity = QManyToManyBidirectionToEntity.manyToManyBidirectionToEntity;
	@PersistenceContext
	private EntityManager entityManager;

	public void save(ManyToManyBidirectionFromEntity entity) {
		entityManager.persist(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public boolean exists(Integer id) {
		return getJpaQueryFactory().select(qFromEntity.count())
				.from(qFromEntity).where(qFromEntity.id.eq(id)).fetchCount() > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Tuple> where(Integer id, Expression<?>... columns) {
		return getJpaQueryFactory().select(columns).from(qFromEntity)
				.where(qFromEntity.id.eq(id)).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Tuple> leftJoin(Integer id, Expression<?>... columns) {
		return getJpaQueryFactory().select(columns).from(qFromEntity)
				.leftJoin(qFromEntity.tos, qToEntity)
				.where(qFromEntity.id.eq(id)).fetch();
	}

	private JPAQueryFactory getJpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}

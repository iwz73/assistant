package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QBasicTypeEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
@Transactional
public class BasicTypeService {
	private QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
	@PersistenceContext
	private EntityManager entityManager;

	public void save(BasicTypeEntity entity) {
		entityManager.persist(entity);
	}

	public void saveRollback(BasicTypeEntity entity) {
		entityManager.persist(entity);
		throw new RuntimeException();
	}

	public long delete(Integer id) {
		return getJpaQueryFactory().delete(qEntity).where(qEntity.id.eq(id))
				.execute();
	}

	public long deleteAll() {
		return getJpaQueryFactory().delete(qEntity).execute();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public BasicTypeEntity findOne(Integer id) {
		return getJpaQueryFactory().selectFrom(qEntity)
				.where(qEntity.id.eq(id)).fetchOne();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BasicTypeEntity> findAll() {
		return getJpaQueryFactory().selectFrom(qEntity).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Iterable<BasicTypeEntity> findAllDescentById(String string) {
		return getJpaQueryFactory().selectFrom(qEntity)
				.orderBy(qEntity.id.desc()).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countByString(String string) {
		return getJpaQueryFactory().select(qEntity.count()).from(qEntity)
				.where(qEntity.string.eq(string)).fetchCount();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public boolean exists(String string) {
		return getJpaQueryFactory().select(qEntity.count()).from(qEntity)
				.where(qEntity.string.eq(string)).fetchCount() > 0;
	}

	private JPAQueryFactory getJpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}

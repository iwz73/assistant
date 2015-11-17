package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QBasicTypeEntity;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
@Transactional
public class BasicTypeService {
	private QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	@Autowired
	private SessionFactory sessionFactory;

	public void save(BasicTypeEntity entity) {
		sessionFactory.openSession().persist(entity);
	}

	public void saveRollback(BasicTypeEntity entity) {
		sessionFactory.openSession().persist(entity);
		throw new RuntimeException();
	}

	public long delete(Integer id) {
		return jpaQueryFactory.delete(qEntity).where(qEntity.id.eq(id))
				.execute();
	}

	public long deleteAll() {
		return jpaQueryFactory.delete(qEntity).execute();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public BasicTypeEntity findOne(Integer id) {
		return jpaQueryFactory.selectFrom(qEntity).where(qEntity.id.eq(id))
				.fetchOne();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<BasicTypeEntity> findAll() {
		return jpaQueryFactory.selectFrom(qEntity).fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Iterable<BasicTypeEntity> findAllDescentById(String string) {
		return jpaQueryFactory.selectFrom(qEntity).orderBy(qEntity.id.desc())
				.fetch();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public long countByString(String string) {
		return jpaQueryFactory.select(qEntity.count()).from(qEntity)
				.where(qEntity.string.eq(string)).fetchCount();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public boolean exists(String string) {
		return jpaQueryFactory.select(qEntity.count()).from(qEntity)
				.where(qEntity.string.eq(string)).fetchCount() > 0;
	}

}

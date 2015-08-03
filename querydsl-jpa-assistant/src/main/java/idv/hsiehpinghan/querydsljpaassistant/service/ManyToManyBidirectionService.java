package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionToEntity;
import idv.hsiehpinghan.querydsljpaassistant.repository.ManyToManyBidirectionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQueryFactory;
import com.mysema.query.types.Expression;

@Service
@Transactional
public class ManyToManyBidirectionService {

	@Autowired
	private ManyToManyBidirectionRepository repository;
	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	public ManyToManyBidirectionFromEntity save(
			ManyToManyBidirectionFromEntity entity) {
		return repository.save(entity);
	}

	public boolean exists(Integer id) {
		return repository.exists(id);
	}

	public List<Tuple> where(Integer id, Expression<?>... columns) {
		QManyToManyBidirectionFromEntity qFrom = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
		return jpaQueryFactory.from(qFrom).where(qFrom.id.eq(id)).list(columns);
	}

	public List<Tuple> leftJoin(Integer id, Expression<?>... columns) {
		QManyToManyBidirectionFromEntity qFrom = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
		QManyToManyBidirectionToEntity qTo = QManyToManyBidirectionToEntity.manyToManyBidirectionToEntity;
		return jpaQueryFactory.from(qFrom).leftJoin(qFrom.tos, qTo)
				.where(qFrom.id.eq(id)).list(columns);
	}

}

package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionToEntity;
import idv.hsiehpinghan.querydsljpaassistant.repository.ManyToManyBidirectionRepository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.sql.PostgresTemplates;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;

@Service
@Transactional
public class ManyToManyBidirectionService {
//	@Autowired
//	private SessionFactory sessionFactory;
	@Autowired
	private ManyToManyBidirectionRepository repository;
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	public ManyToManyBidirectionFromEntity save(
			ManyToManyBidirectionFromEntity entity) {
		return repository.save(entity);
	}

	public boolean exists(Integer id) {
		return repository.exists(id);
	}

	public List<Tuple> where(Integer id) throws SQLException {
		QManyToManyBidirectionFromEntity qFrom = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
		JPAQuery  query = new JPAQuery(entityManagerFactory.getObject().createEntityManager());
		query = query.from(qFrom);
		query = query.where(qFrom.id.eq(id));
		return query.list(qFrom.id, qFrom.name);
		// return query.from(qFrom).where(qFrom.id.eq(id)).list();
	}

	public List<Tuple> leftJoin() {
		SQLTemplates template = new PostgresTemplates();
		SQLQuery query = new SQLQuery(template);
		QManyToManyBidirectionFromEntity qFrom = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
		QManyToManyBidirectionToEntity qTo = QManyToManyBidirectionToEntity.manyToManyBidirectionToEntity;
		return query.from(qFrom).leftJoin(qTo)
				.list(qFrom.id, qFrom.name, qTo.id, qTo.name);
	}

}

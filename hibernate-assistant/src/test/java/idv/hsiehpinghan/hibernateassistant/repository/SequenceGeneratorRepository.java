package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.SequenceGeneratorEntity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceGeneratorRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(SequenceGeneratorEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public SequenceGeneratorEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (SequenceGeneratorEntity) session.get(
				SequenceGeneratorEntity.class, id);
	}

	public int dropTable() {
		Session session = sessionFactory.getCurrentSession();
		Query generatorQuery = session
				.createSQLQuery("drop sequence {h-schema}sequence_generator_sequence_name");
		generatorQuery.executeUpdate();
		String table = StringUtils.uncapitalize(SequenceGeneratorEntity.class
				.getSimpleName());
		Query query = session.createSQLQuery(String.format("drop table {h-schema}%s",
				table));
		return query.executeUpdate();
	}

}

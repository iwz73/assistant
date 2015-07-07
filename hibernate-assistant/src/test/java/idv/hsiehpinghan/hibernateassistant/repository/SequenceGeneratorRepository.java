package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.SequenceGeneratorEntity;

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

}

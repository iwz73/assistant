package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinTableManyEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneJoinTableRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ManyToOneJoinTableManyEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ManyToOneJoinTableManyEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToOneJoinTableManyEntity) session.get(
				ManyToOneJoinTableManyEntity.class, id);
	}

}

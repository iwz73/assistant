package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnManyEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneJoinColumnRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ManyToOneJoinColumnManyEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ManyToOneJoinColumnManyEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToOneJoinColumnManyEntity) session.get(
				ManyToOneJoinColumnManyEntity.class, id);
	}

}

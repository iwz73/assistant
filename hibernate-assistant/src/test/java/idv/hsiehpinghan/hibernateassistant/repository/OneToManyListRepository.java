package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyListOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyListRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyListOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyListOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyListOneEntity) session.get(
				OneToManyListOneEntity.class, id);
	}

}

package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyMapRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyMapOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyMapOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyMapOneEntity) session.get(OneToManyMapOneEntity.class,
				id);
	}

}

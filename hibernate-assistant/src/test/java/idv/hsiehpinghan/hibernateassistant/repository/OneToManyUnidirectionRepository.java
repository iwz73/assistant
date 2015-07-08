package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyUnidirectionOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyUnidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyUnidirectionOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyUnidirectionOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyUnidirectionOneEntity) session.get(
				OneToManyUnidirectionOneEntity.class, id);
	}

}

package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapKeyOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyMapKeyRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyMapKeyOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyMapKeyOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyMapKeyOneEntity) session.get(
				OneToManyMapKeyOneEntity.class, id);
	}

}

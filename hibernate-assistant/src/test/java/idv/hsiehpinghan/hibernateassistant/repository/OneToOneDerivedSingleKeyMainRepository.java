package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneDerivedSingleKeyMainEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOneDerivedSingleKeyMainRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOneDerivedSingleKeyMainEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOneDerivedSingleKeyMainEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOneDerivedSingleKeyMainEntity) session.get(
				OneToOneDerivedSingleKeyMainEntity.class, id);
	}
}

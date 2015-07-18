package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneDerivedSingleKeyDerivedEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOneDerivedSingleKeyDerivedRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOneDerivedSingleKeyDerivedEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOneDerivedSingleKeyDerivedEntity findOne(int i) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOneDerivedSingleKeyDerivedEntity) session.get(
				OneToOneDerivedSingleKeyDerivedEntity.class, i);
	}
}

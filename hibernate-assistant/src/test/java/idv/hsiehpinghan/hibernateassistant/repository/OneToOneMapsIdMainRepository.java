package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneMapsIdMainEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOneMapsIdMainRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOneMapsIdMainEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOneMapsIdMainEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOneMapsIdMainEntity) session.get(
				OneToOneMapsIdMainEntity.class, id);
	}
}

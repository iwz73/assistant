package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyBidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyBidirectionOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyBidirectionOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyBidirectionOneEntity) session.get(
				OneToManyBidirectionOneEntity.class, id);
	}

}

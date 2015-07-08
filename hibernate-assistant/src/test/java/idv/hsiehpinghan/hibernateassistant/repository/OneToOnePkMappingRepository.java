package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOnePkMappingRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOnePkMappingFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOnePkMappingFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOnePkMappingFromEntity) session.get(
				OneToOnePkMappingFromEntity.class, id);
	}

}

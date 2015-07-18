package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneMapsIdMapperEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOneMapsIdMapperRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOneMapsIdMapperEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOneMapsIdMapperEntity findOne(int i) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOneMapsIdMapperEntity) session.get(
				OneToOneMapsIdMapperEntity.class, i);
	}
}

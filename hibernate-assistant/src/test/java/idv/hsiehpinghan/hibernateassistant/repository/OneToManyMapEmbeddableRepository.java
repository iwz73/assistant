package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapEmbeddableOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyMapEmbeddableRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyMapEmbeddableOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyMapEmbeddableOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyMapEmbeddableOneEntity) session.get(
				OneToManyMapEmbeddableOneEntity.class, id);
	}

}

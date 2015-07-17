package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyMapEmbeddableRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ManyToManyMapEmbeddableFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ManyToManyMapEmbeddableFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToManyMapEmbeddableFromEntity) session.get(
				ManyToManyMapEmbeddableFromEntity.class, id);
	}

}

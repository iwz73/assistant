package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdOneIdEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyDerivedEmbeddedIdRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyDerivedEmbeddedIdOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyDerivedEmbeddedIdOneEntity findOne(
			OneToManyDerivedEmbeddedIdOneIdEntity id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyDerivedEmbeddedIdOneEntity) session.get(
				OneToManyDerivedEmbeddedIdOneEntity.class, id);
	}

	public void delete(OneToManyDerivedEmbeddedIdOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
	}
}

package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdEmbeddableEntity;

@Repository
public class EmbeddedIdRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(EmbeddedIdContainerEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public EmbeddedIdContainerEntity findOne(EmbeddedIdEmbeddableEntity id) {
		Session session = sessionFactory.getCurrentSession();
		return (EmbeddedIdContainerEntity) session.get(EmbeddedIdContainerEntity.class, id);
	}

}

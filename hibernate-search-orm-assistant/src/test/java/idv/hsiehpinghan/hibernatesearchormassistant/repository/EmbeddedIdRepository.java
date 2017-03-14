package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
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

	public int deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		String table = EmbeddedIdContainerEntity.class.getSimpleName();
		String hql = String.format("delete from %s", table);
		Query query = session.createQuery(hql);
		return query.executeUpdate();
	}

	public int reindexAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<EmbeddedIdContainerEntity> query = session.createQuery("from EmbeddedIdContainerEntity ",
				EmbeddedIdContainerEntity.class);
		List<EmbeddedIdContainerEntity> entities = query.getResultList();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		for (EmbeddedIdContainerEntity entity : entities) {
			fullTextSession.index(entity);
		}
		return entities.size();
	}
}

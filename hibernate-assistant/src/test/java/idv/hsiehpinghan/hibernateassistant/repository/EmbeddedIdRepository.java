package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdEmbeddableEntity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		return (EmbeddedIdContainerEntity) session.get(
				EmbeddedIdContainerEntity.class, id);
	}

	public int deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		String table = EmbeddedIdContainerEntity.class.getSimpleName();
		String hql = String.format("delete from %s", table);
		Query query = session.createQuery(hql);
		return query.executeUpdate();
	}
}

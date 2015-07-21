package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdFromIdEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyCompoundIdRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(ManyToManyCompoundIdFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public ManyToManyCompoundIdFromEntity findOne(
			ManyToManyCompoundIdFromIdEntity id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToManyCompoundIdFromEntity) session.get(
				ManyToManyCompoundIdFromEntity.class, id);
	}

}

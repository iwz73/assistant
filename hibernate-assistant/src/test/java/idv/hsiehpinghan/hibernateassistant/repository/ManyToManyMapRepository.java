package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyMapRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(ManyToManyMapFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public ManyToManyMapFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToManyMapFromEntity) session.get(
				ManyToManyMapFromEntity.class, id);
	}

}

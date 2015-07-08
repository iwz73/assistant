package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyJoinTableRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(ManyToManyJoinTableFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public ManyToManyJoinTableFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToManyJoinTableFromEntity) session.get(
				ManyToManyJoinTableFromEntity.class, id);
	}

}

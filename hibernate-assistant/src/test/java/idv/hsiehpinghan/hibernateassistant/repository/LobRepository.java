package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.LobEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LobRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(LobEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public LobEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (LobEntity) session.get(LobEntity.class, id);
	}

}

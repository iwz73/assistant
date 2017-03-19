package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.BridgeEntity;

@Repository
public class BridgeEntityRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(BridgeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public BridgeEntity findOne(BridgeEntity.BridgeEntityId id) {
		Session session = sessionFactory.getCurrentSession();
		return (BridgeEntity) session.get(BridgeEntity.class, id);
	}

}

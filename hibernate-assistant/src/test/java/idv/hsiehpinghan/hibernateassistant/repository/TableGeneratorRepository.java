package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.TableGeneratorEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TableGeneratorRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(TableGeneratorEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public TableGeneratorEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (TableGeneratorEntity) session.get(TableGeneratorEntity.class,
				id);
	}

}

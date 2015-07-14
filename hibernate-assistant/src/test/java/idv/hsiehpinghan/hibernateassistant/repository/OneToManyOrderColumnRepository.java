package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyOrderColumnOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToManyOrderColumnRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToManyOrderColumnOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToManyOrderColumnOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToManyOrderColumnOneEntity) session.get(
				OneToManyOrderColumnOneEntity.class, id);
	}

}

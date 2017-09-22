package idv.hsiehpinghan.springsecurityassistant.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.springsecurityassistant.entity.UserEntity;

@Repository
public class UserRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(UserEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public UserEntity findOne(String username) {
		Session session = sessionFactory.getCurrentSession();
		return (UserEntity) session.get(UserEntity.class, username);
	}

}

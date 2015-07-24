package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.JoinedInheritance_2_A_Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinedInheritance_2_A_Repository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(JoinedInheritance_2_A_Entity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public JoinedInheritance_2_A_Entity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (JoinedInheritance_2_A_Entity) session.get(
				JoinedInheritance_2_A_Entity.class, id);
	}
}

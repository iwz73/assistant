package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.JoinedTableInheritance_1_A_Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinedTableInheritance_1_A_Repository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(JoinedTableInheritance_1_A_Entity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public JoinedTableInheritance_1_A_Entity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (JoinedTableInheritance_1_A_Entity) session.get(
				JoinedTableInheritance_1_A_Entity.class, id);
	}
}

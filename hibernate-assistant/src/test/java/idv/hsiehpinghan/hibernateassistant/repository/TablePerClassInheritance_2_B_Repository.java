package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.TablePerClassInheritance_2_B_Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TablePerClassInheritance_2_B_Repository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(TablePerClassInheritance_2_B_Entity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public TablePerClassInheritance_2_B_Entity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (TablePerClassInheritance_2_B_Entity) session.get(
				TablePerClassInheritance_2_B_Entity.class, id);
	}
}

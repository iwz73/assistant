package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.MixedInheritance_2_A_Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MixedInheritance_2_A_Repository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(MixedInheritance_2_A_Entity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public MixedInheritance_2_A_Entity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (MixedInheritance_2_A_Entity) session.get(
				MixedInheritance_2_A_Entity.class, id);
	}
}

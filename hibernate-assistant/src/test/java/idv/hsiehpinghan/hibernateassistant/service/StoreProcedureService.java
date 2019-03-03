package idv.hsiehpinghan.hibernateassistant.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoreProcedureService {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Integer> callBasicProcedure(String inputParameter) {
		Session session = sessionFactory.getCurrentSession();
		// @formatter:off
		Query query = session
			.createSQLQuery("CALL basic_procedure(:inputParameter, :inputOutputParameter);")
			.setParameter("inputParameter", inputParameter)
			.setParameter("inputOutputParameter", 0);
		// @formatter:on
		@SuppressWarnings("unchecked")
		List<Integer> result = query.list();
		return result;
	}

}

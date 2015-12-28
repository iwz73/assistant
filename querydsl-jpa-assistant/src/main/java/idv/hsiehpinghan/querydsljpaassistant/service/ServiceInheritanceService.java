package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.QServiceInheritanceEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.ServiceInheritanceEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class ServiceInheritanceService extends CommonServiceInheritanceService {
	private QServiceInheritanceEntity qEntity = QServiceInheritanceEntity.serviceInheritanceEntity;
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ServiceInheritanceEntity findOne(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return getHibernateQueryFactory(session).selectFrom(qEntity)
				.where(qEntity.id.eq(id)).fetchOne();
	}

}

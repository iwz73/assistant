package idv.hsiehpinghan.springbatchassistant.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.springbatchassistant.entity.HibernateEntity;
import idv.hsiehpinghan.springbatchassistant.utility.InputStreamUtility;
import idv.hsiehpinghan.springbatchassistant.utility.ReaderUtility;

@Repository
public class HibernateRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(HibernateEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public HibernateEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (HibernateEntity) session.get(HibernateEntity.class, id);
	}

	public String findClobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select clob from HibernateEntity bte where bte.id = :id ");
		query.setParameter("id", id);
		Clob clob = (Clob) query.uniqueResult();
		return convertToString(clob);
	}

	public String findBlobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select blob from HibernateEntity bte where bte.id = :id ");
		query.setParameter("id", id);
		Blob blob = (Blob) query.uniqueResult();
		return convertToString(blob);
	}

	private String convertToString(java.sql.Clob clob) throws SQLException, IOException {
		Reader reader = clob.getCharacterStream();
		return ReaderUtility.readAsString(reader);
	}

	private String convertToString(java.sql.Blob blob) throws SQLException, IOException {
		InputStream inputStream = blob.getBinaryStream();
		return InputStreamUtility.readAsString(inputStream);
	}
}

package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.InputStreamUtility;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.ReaderUtility;

@Repository
public class BasicTypeRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(BasicTypeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public BasicTypeEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (BasicTypeEntity) session.get(BasicTypeEntity.class, id);
	}

//	public String findClobAsString(int id) throws SQLException, IOException {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Clob> query = session.createQuery("select clob from BasicTypeEntity bte where bte.id = :id ", Clob.class);
//		query.setParameter("id", id);
//		Clob clob = query.uniqueResult();
//		return convertToString(clob);
//	}
//
//	public String findBlobAsString(int id) throws SQLException, IOException {
//		Session session = sessionFactory.getCurrentSession();
//		Query<Blob> query = session.createQuery("select blob from BasicTypeEntity bte where bte.id = :id ", Blob.class);
//		query.setParameter("id", id);
//		Blob blob = query.uniqueResult();
//		return convertToString(blob);
//	}

	public String findClobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select clob from BasicTypeEntity bte where bte.id = :id ");
		query.setParameter("id", id);
		
		System.err.println(query.uniqueResult().getClass() + " !!!");
		
		Clob clob = (Clob)query.uniqueResult();
		return convertToString(clob);
	}

	public String findBlobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select blob from BasicTypeEntity bte where bte.id = :id ");
		query.setParameter("id", id);
		
		System.err.println(query.uniqueResult().getClass() + " !!!");
		
		Blob blob = (Blob)query.uniqueResult();
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

package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.streamutility.utility.ReaderUtility;

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

	public String findClobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select clob from BasicTypeEntity bte where bte.id = :id ");
		query.setParameter("id", id);
		Clob clob = (Clob) query.uniqueResult();
		return convertToString(clob);
	}

	public String findBlobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select blob from BasicTypeEntity bte where bte.id = :id ");
		query.setParameter("id", id);
		Blob blob = (Blob) query.uniqueResult();
		return convertToString(blob);
	}

	private String convertToString(java.sql.Clob clob) throws SQLException,
			IOException {
		Reader reader = clob.getCharacterStream();
		return ReaderUtility.readAsString(reader);
	}

	private String convertToString(java.sql.Blob blob) throws SQLException,
			IOException {
		InputStream inputStream = blob.getBinaryStream();
		return ReaderUtility.readAsString(inputStream);
	}
}

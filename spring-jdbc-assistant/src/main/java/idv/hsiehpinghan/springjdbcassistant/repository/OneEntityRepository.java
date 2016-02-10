package idv.hsiehpinghan.springjdbcassistant.repository;

import idv.hsiehpinghan.springjdbcassistant.entity.OneEntity;

import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OneEntityRepository {
	@Autowired
	private DataSource dataSource;
	
	public void insert(OneEntity one) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO spring_jdbc_assistant.one_entity(id, name) VALUES (?, ?);";
//		PreparedStatement p;
//		p.setShort(parameterIndex, x);
//		jdbcTemplate.update(psc)
//		http://www.journaldev.com/2603/spring-transaction-management-example-with-jdbc
	}
}

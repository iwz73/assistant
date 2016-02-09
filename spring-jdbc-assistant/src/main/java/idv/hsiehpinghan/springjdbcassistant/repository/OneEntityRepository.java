package idv.hsiehpinghan.springjdbcassistant.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OneEntityRepository {
	@Autowired
	private DataSource dataSource;
	
	public void insert() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		http://www.journaldev.com/2603/spring-transaction-management-example-with-jdbc
	}
}

package idv.hsiehpinghan.jooqassistant.test;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jooqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jooqassistant.service.BookService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TransactionTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private BookService bookService;

	@Test
	public void rollback() throws SQLException {
		Integer id = Long.valueOf(System.currentTimeMillis()).intValue();
		System.err.println(id);
		try {
			bookService.save(id);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			// do nothing
		}
		Assert.assertFalse(bookService.isExists(id));
	}

}

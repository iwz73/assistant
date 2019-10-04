package idv.hsiehpinghan.jooqassistant.test;

import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jooqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jooqassistant.jooq.tables.daos.BookStoreDao;
import idv.hsiehpinghan.jooqassistant.jooq.tables.pojos.BookStore;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DaoTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private Configuration jooqConfiguration;

	@Test
	public void insert() {
		String name = "name_" + System.currentTimeMillis();
		BookStoreDao bookStoreDao = new BookStoreDao(jooqConfiguration);
		BookStore bookStore = new BookStore(null, name);
		bookStoreDao.insert(bookStore);
		Assert.assertTrue(bookStoreDao.existsById(name));
	}

}

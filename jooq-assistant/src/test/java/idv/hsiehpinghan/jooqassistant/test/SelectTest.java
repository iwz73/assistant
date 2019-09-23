package idv.hsiehpinghan.jooqassistant.test;

import static idv.hsiehpinghan.jooqassistant.jooq.tables.Author.AUTHOR;
import static idv.hsiehpinghan.jooqassistant.jooq.tables.Book.BOOK;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jooqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jooqassistant.jooq.tables.records.AuthorRecord;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SelectTest extends AbstractTestNGSpringContextTests {
	@Value("${userName}")
	private String userName;
	@Value("${password}")
	private String password;
	@Value("${url}")
	private String url;

	@Test
	public void selectAll() throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext dslContext = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> result = dslContext.select().from(AUTHOR).fetch();
			for (Record r : result) {
				Integer id = r.getValue(AUTHOR.ID);
				String firstName = r.getValue(AUTHOR.FIRST_NAME);
				String lastName = r.getValue(AUTHOR.LAST_NAME);
				Date dateOfBirth = r.getValue(AUTHOR.DATE_OF_BIRTH);
				Integer yearOfBirth = r.getValue(AUTHOR.YEAR_OF_BIRTH);
				Byte distinguished = r.getValue(AUTHOR.DISTINGUISHED);
				System.err.println("id: " + id + "; firstName: " + firstName + "; lastName: " + lastName
						+ "; dateOfBirth:" + dateOfBirth + "; yearOfBirth:" + yearOfBirth + "; distinguished:"
						+ distinguished + ";");
			}
			Assert.assertTrue(0 < result.size());
		}
	}

	@Test
	public void join() throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext dslContext = DSL.using(conn, SQLDialect.MYSQL);
			// @formatter:off
			Result<Record3<String, String, String>> result =dslContext
				.select(BOOK.TITLE, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
				.from(BOOK)
				.join(AUTHOR).on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
				.where(BOOK.PUBLISHED_IN.eq(1948))
				.fetch();
			// @formatter:on
			for (Record r : result) {
				String title = r.getValue(BOOK.TITLE);
				String firstName = r.getValue(AUTHOR.FIRST_NAME);
				String lastName = r.getValue(AUTHOR.LAST_NAME);
				System.err.println("title: " + title + "; firstName: " + firstName + "; lastName: " + lastName + ";");
			}
			Assert.assertTrue(0 < result.size());
		}
	}

	@Test
	public void fetchOne() throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext dslContext = DSL.using(conn, SQLDialect.MYSQL);
			AuthorRecord author = dslContext.fetchOne(AUTHOR, AUTHOR.ID.eq(1));
			Integer id = author.getValue(AUTHOR.ID);
			String firstName = author.getValue(AUTHOR.FIRST_NAME);
			String lastName = author.getValue(AUTHOR.LAST_NAME);
			Date dateOfBirth = author.getValue(AUTHOR.DATE_OF_BIRTH);
			Integer yearOfBirth = author.getValue(AUTHOR.YEAR_OF_BIRTH);
			Byte distinguished = author.getValue(AUTHOR.DISTINGUISHED);
			System.err.println("id: " + id + "; firstName: " + firstName + "; lastName: " + lastName + "; dateOfBirth:"
					+ dateOfBirth + "; yearOfBirth:" + yearOfBirth + "; distinguished:" + distinguished + ";");
			Assert.assertNotNull(author);
		}
	}
}

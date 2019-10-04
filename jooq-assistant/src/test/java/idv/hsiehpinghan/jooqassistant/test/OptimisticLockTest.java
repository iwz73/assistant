package idv.hsiehpinghan.jooqassistant.test;

import static idv.hsiehpinghan.jooqassistant.jooq.tables.Book.BOOK;

import org.jooq.DSLContext;
import org.jooq.exception.DataChangedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jooqassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jooqassistant.jooq.tables.records.BookRecord;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class OptimisticLockTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private DSLContext dslContext;

	@Test(expectedExceptions = { DataChangedException.class })
	public void test() {
		BookRecord book1 = dslContext.fetchOne(BOOK, BOOK.ID.eq(3));
		BookRecord book2 = dslContext.fetchOne(BOOK, BOOK.ID.eq(3));

		book1.setTitle("title_1");
		book1.store();

		book2.setTitle("title_2");
		book2.store();
	}

}

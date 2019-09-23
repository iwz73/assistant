package idv.hsiehpinghan.jooqassistant.service;

import static idv.hsiehpinghan.jooqassistant.jooq.tables.Book.BOOK;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {
	@Autowired
	private DSLContext dslContext;

	public void save(Integer id) {
		for (int i = 0; i < 2; i++) {
			// @formatter:off
        	dslContext.insertInto(BOOK)
            .set(BOOK.ID, id)
            .set(BOOK.AUTHOR_ID, 1)
            .set(BOOK.TITLE, "Book 5")
            .set(BOOK.PUBLISHED_IN, 3)
            .set(BOOK.LANGUAGE_ID, 4)
            .execute();
        	// @formatter:on
		}
	}

	@Transactional(readOnly = true)
	public boolean isExists(Integer id) {
		// @formatter:off
		return dslContext.fetchExists(
			dslContext
				.select()
				.from(BOOK)
				.where(BOOK.ID.eq(id))
		);
		// @formatter:on
	}
}

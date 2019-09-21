/*
 * This file is generated by jOOQ.
 */
package idv.hsiehpinghan.jooqassistant.jooq;


import idv.hsiehpinghan.jooqassistant.jooq.tables.Author;
import idv.hsiehpinghan.jooqassistant.jooq.tables.AuthorBook;
import idv.hsiehpinghan.jooqassistant.jooq.tables.Book;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>jooq_assistant</code> 
 * schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index AUTHOR_PRIMARY = Indexes0.AUTHOR_PRIMARY;
    public static final Index AUTHOR_BOOK_FK_AB_BOOK = Indexes0.AUTHOR_BOOK_FK_AB_BOOK;
    public static final Index AUTHOR_BOOK_PRIMARY = Indexes0.AUTHOR_BOOK_PRIMARY;
    public static final Index BOOK_PRIMARY = Indexes0.BOOK_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index AUTHOR_PRIMARY = Internal.createIndex("PRIMARY", Author.AUTHOR, new OrderField[] { Author.AUTHOR.ID }, true);
        public static Index AUTHOR_BOOK_FK_AB_BOOK = Internal.createIndex("fk_ab_book", AuthorBook.AUTHOR_BOOK, new OrderField[] { AuthorBook.AUTHOR_BOOK.BOOK_ID }, false);
        public static Index AUTHOR_BOOK_PRIMARY = Internal.createIndex("PRIMARY", AuthorBook.AUTHOR_BOOK, new OrderField[] { AuthorBook.AUTHOR_BOOK.AUTHOR_ID, AuthorBook.AUTHOR_BOOK.BOOK_ID }, true);
        public static Index BOOK_PRIMARY = Internal.createIndex("PRIMARY", Book.BOOK, new OrderField[] { Book.BOOK.ID }, true);
    }
}

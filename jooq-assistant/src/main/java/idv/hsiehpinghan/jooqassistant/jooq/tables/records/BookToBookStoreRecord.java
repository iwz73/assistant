/*
 * This file is generated by jOOQ.
 */
package idv.hsiehpinghan.jooqassistant.jooq.tables.records;


import idv.hsiehpinghan.jooqassistant.jooq.tables.BookToBookStore;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookToBookStoreRecord extends UpdatableRecordImpl<BookToBookStoreRecord> implements Record3<String, Integer, Integer> {

    private static final long serialVersionUID = 650942349;

    /**
     * Setter for <code>jooq_assistant.book_to_book_store.name</code>.
     */
    public void setName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>jooq_assistant.book_to_book_store.name</code>.
     */
    public String getName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>jooq_assistant.book_to_book_store.book_id</code>.
     */
    public void setBookId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>jooq_assistant.book_to_book_store.book_id</code>.
     */
    public Integer getBookId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>jooq_assistant.book_to_book_store.stock</code>.
     */
    public void setStock(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>jooq_assistant.book_to_book_store.stock</code>.
     */
    public Integer getStock() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return BookToBookStore.BOOK_TO_BOOK_STORE.NAME;
    }

    @Override
    public Field<Integer> field2() {
        return BookToBookStore.BOOK_TO_BOOK_STORE.BOOK_ID;
    }

    @Override
    public Field<Integer> field3() {
        return BookToBookStore.BOOK_TO_BOOK_STORE.STOCK;
    }

    @Override
    public String component1() {
        return getName();
    }

    @Override
    public Integer component2() {
        return getBookId();
    }

    @Override
    public Integer component3() {
        return getStock();
    }

    @Override
    public String value1() {
        return getName();
    }

    @Override
    public Integer value2() {
        return getBookId();
    }

    @Override
    public Integer value3() {
        return getStock();
    }

    @Override
    public BookToBookStoreRecord value1(String value) {
        setName(value);
        return this;
    }

    @Override
    public BookToBookStoreRecord value2(Integer value) {
        setBookId(value);
        return this;
    }

    @Override
    public BookToBookStoreRecord value3(Integer value) {
        setStock(value);
        return this;
    }

    @Override
    public BookToBookStoreRecord values(String value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookToBookStoreRecord
     */
    public BookToBookStoreRecord() {
        super(BookToBookStore.BOOK_TO_BOOK_STORE);
    }

    /**
     * Create a detached, initialised BookToBookStoreRecord
     */
    public BookToBookStoreRecord(String name, Integer bookId, Integer stock) {
        super(BookToBookStore.BOOK_TO_BOOK_STORE);

        set(0, name);
        set(1, bookId);
        set(2, stock);
    }
}

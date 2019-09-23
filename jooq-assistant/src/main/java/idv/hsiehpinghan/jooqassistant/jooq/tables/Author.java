/*
 * This file is generated by jOOQ.
 */
package idv.hsiehpinghan.jooqassistant.jooq.tables;


import idv.hsiehpinghan.jooqassistant.jooq.Indexes;
import idv.hsiehpinghan.jooqassistant.jooq.JooqAssistant;
import idv.hsiehpinghan.jooqassistant.jooq.Keys;
import idv.hsiehpinghan.jooqassistant.jooq.tables.records.AuthorRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Author extends TableImpl<AuthorRecord> {

    private static final long serialVersionUID = 1926645741;

    /**
     * The reference instance of <code>jooq_assistant.author</code>
     */
    public static final Author AUTHOR = new Author();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthorRecord> getRecordType() {
        return AuthorRecord.class;
    }

    /**
     * The column <code>jooq_assistant.author.id</code>.
     */
    public final TableField<AuthorRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>jooq_assistant.author.first_name</code>.
     */
    public final TableField<AuthorRecord, String> FIRST_NAME = createField(DSL.name("first_name"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>jooq_assistant.author.last_name</code>.
     */
    public final TableField<AuthorRecord, String> LAST_NAME = createField(DSL.name("last_name"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>jooq_assistant.author.date_of_birth</code>.
     */
    public final TableField<AuthorRecord, Date> DATE_OF_BIRTH = createField(DSL.name("date_of_birth"), org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>jooq_assistant.author.year_of_birth</code>.
     */
    public final TableField<AuthorRecord, Integer> YEAR_OF_BIRTH = createField(DSL.name("year_of_birth"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>jooq_assistant.author.distinguished</code>.
     */
    public final TableField<AuthorRecord, Byte> DISTINGUISHED = createField(DSL.name("distinguished"), org.jooq.impl.SQLDataType.TINYINT, this, "");

    /**
     * Create a <code>jooq_assistant.author</code> table reference
     */
    public Author() {
        this(DSL.name("author"), null);
    }

    /**
     * Create an aliased <code>jooq_assistant.author</code> table reference
     */
    public Author(String alias) {
        this(DSL.name(alias), AUTHOR);
    }

    /**
     * Create an aliased <code>jooq_assistant.author</code> table reference
     */
    public Author(Name alias) {
        this(alias, AUTHOR);
    }

    private Author(Name alias, Table<AuthorRecord> aliased) {
        this(alias, aliased, null);
    }

    private Author(Name alias, Table<AuthorRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Author(Table<O> child, ForeignKey<O, AuthorRecord> key) {
        super(child, key, AUTHOR);
    }

    @Override
    public Schema getSchema() {
        return JooqAssistant.JOOQ_ASSISTANT;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.AUTHOR_PRIMARY);
    }

    @Override
    public UniqueKey<AuthorRecord> getPrimaryKey() {
        return Keys.KEY_AUTHOR_PRIMARY;
    }

    @Override
    public List<UniqueKey<AuthorRecord>> getKeys() {
        return Arrays.<UniqueKey<AuthorRecord>>asList(Keys.KEY_AUTHOR_PRIMARY);
    }

    @Override
    public Author as(String alias) {
        return new Author(DSL.name(alias), this);
    }

    @Override
    public Author as(Name alias) {
        return new Author(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Author rename(String name) {
        return new Author(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Author rename(Name name) {
        return new Author(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, Date, Integer, Byte> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}

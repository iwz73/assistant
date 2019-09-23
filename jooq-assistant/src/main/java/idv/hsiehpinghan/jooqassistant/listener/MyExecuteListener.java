package idv.hsiehpinghan.jooqassistant.listener;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

/**
 * copy from
 * https://github.com/jOOQ/jOOQ/blob/master/jOOQ-examples/jOOQ-spring-example/src/main/java/org/jooq/example/spring/exception/ExceptionTranslator.java
 */
public class MyExecuteListener extends DefaultExecuteListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void exception(ExecuteContext ctx) {

		// [#4391] Translate only SQLExceptions
		if (ctx.sqlException() != null) {
			SQLDialect dialect = ctx.dialect();
			SQLExceptionTranslator translator = (dialect != null)
					? new SQLErrorCodeSQLExceptionTranslator(dialect.thirdParty().springDbName())
					: new SQLStateSQLExceptionTranslator();

			ctx.exception(translator.translate("jOOQ", ctx.sql(), ctx.sqlException()));
		}
	}
}

package idv.hsiehpinghan.jooqassistant;

import static idv.hsiehpinghan.jooqassistant.jooq.tables.Author.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class Main {
    public static void main(String[] args) {
        String userName = "root";
        String password = "%j6w1oW";
        String url = "jdbc:mysql://localhost:3306/joop_assistant";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
        	DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        	Result<Record> result = create.select().from(AUTHOR).fetch();
        	for (Record r : result) {
        	    Integer id = r.getValue(AUTHOR.ID);
        	    String firstName = r.getValue(AUTHOR.FIRST_NAME);
        	    String lastName = r.getValue(AUTHOR.LAST_NAME);

        	    System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
        	}
        } 

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

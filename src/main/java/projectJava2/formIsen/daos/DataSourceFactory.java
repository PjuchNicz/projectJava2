package projectJava2.formIsen.daos;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceFactory {

    private static SQLiteDataSource dataSource;

    private DataSourceFactory() {
        // This is a static class that should not be instantiated.
        // Here's a way to remember it when this class will have 2K lines and you come
        // back to it in 2 years
        throw new IllegalStateException("This is a static class that should not be instantiated");
    }

    /**
     * @return a connection to the SQLite Database
     *
     */
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:sqlite.db");
        }
        return dataSource;
    }

    public static void initDb() {
        try (Connection connection = getDataSource().getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(
                    """
                            CREATE TABLE IF NOT EXISTS person (\r
                              idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r
                              lastname VARCHAR(45) NOT NULL,\r
                              firstname VARCHAR(45) NOT NULL,\r
                              nickname VARCHAR(45) NOT NULL,\r
                              phone_number VARCHAR(15) NULL,\r
                              address VARCHAR(200) NULL,\r
                              email_address VARCHAR(150) NULL,\r
                              birth_date DATE NULL,\r
                              friend_list VARCHAR(1000) NULL,\r
                              UNIQUE(phone_number, email_address));
                        """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
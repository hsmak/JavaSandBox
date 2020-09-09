package jdbc.metadata;

import java.sql.*;

public class CursorScrollableUpdatable {

    static String url = "jdbc:derby:memory:ConnectionPool_db;create=true";
    static String user = "";
    static String password = "";

    public static void main(String... args) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(metaData.getDriverName() + "...");

        if (metaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
            System.out.print("TYPE_FORWARD_ONLY");
            if (metaData.supportsResultSetConcurrency(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY)) {
                System.out.println(" --> and supports CONCUR_READ_ONLY");
            }
        }

        if (metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
            System.out.print("TYPE_SCROLL_INSENSITIVE");
            if (metaData.supportsResultSetConcurrency(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                System.out.println(" --> and CONCUR_UPDATABLE");
            }
        }

        if (metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
            System.out.print("TYPE_SCROLL_SENSITIVE");
            if (metaData.supportsResultSetConcurrency(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                System.out.println(" --> and CONCUR_UPDATABLE");
            }
        }
    }
}

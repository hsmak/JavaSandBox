package _ocp8.ch12_jdbc;

import java.sql.*;

import static _ocp8.Utils.printClassNameViaStackWalker;
import static _ocp8.Utils.printMethodNameViaStackWalker;

class Ch12AppRunner {
    private static final String URL_DERBY = "jdbc:derby:memory:test_db;create=true";

    /*
     * ToDo: Prepare a DB:
     *  - Create db.sql file
     *  - Load it
     *  - Test it
     */
    static {

    }

    public static void main(String[] args) throws SQLException {
        printClassNameViaStackWalker(1);

        Connection c = DriverManager.getConnection(URL_DERBY);
        Statement s = c.createStatement();

        Connection connection = DriverManager.getConnection(URL_DERBY);
        Statement statement = connection.createStatement();

        /*
         * Return boolean when not sure what the result will be
         *      - True if returns ResultSet
         *      - False otherwise
         *  - Use getResultSet() to retrieve ResultSet if True
         *  - use getUpdateCount() to retrieve rows affected if False
         *
         * Table-Specific:(CREATE TABLE | DROP TABLE) Query-Specific:(INSERT | UPDATE | DELETE)         *
         */
        /*statement.execute();
        statement.executeQuery();   // return ResultSet Query-Specific:(SELECT)
        statement.executeUpdate();  // return int <- # of rows affected Table-Specific:(CREATE TABLE | DROP TABLE) Query-Specific:(INSERT | UPDATE | DELETE)
        statement.executeBatch();
        statement.executeLargeBatch();*/

        statement.execute(
                "CREATE TABLE table_tb " +
                        "    (id INT PRIMARY KEY, " +
                        "    name VARCHAR(12))");
        statement.execute("INSERT INTO table_tb VALUES " +
                "(10,'TEN')," +
                "(20,'TWENTY')," +
                "(30,'THIRTY')");

        ResultSet rs = statement.executeQuery("SELECT * FROM table_tb " +
                "WHERE id=20");
        while (rs.next()) {
            System.out.println(String.format("ResultSet's Row: ID:%d Name:%s", rs.getInt("id"), rs.getString("name")));
        }
    }

    public static void establishConnectionViaDriverManager() {
        printMethodNameViaStackWalker(1);
    }

    /*
     * This is typically used within a Java/Jee Middleware/Container
     * DataSource is obtained via JNDI lookup
     */
    public static void establishConnectionViaDataSource() {
        printMethodNameViaStackWalker(1);
    }

    public static void createStatements() {
        printMethodNameViaStackWalker(1);

    }

    public static void PrepareStatements() {
        printMethodNameViaStackWalker(1);
    }

    public static void prepareCalls() {
        printMethodNameViaStackWalker(1);
    }

}

package _ocp8.ch12_jdbc;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static _ocp8.Utils.printClassNameViaStackWalker;
import static _ocp8.Utils.printMethodNameViaStackWalker;

class JDBCTesting {
    static {
        printMethodNameViaStackWalker(1);
        try {
            Connection connection = DerbyDB.getConnectionViaDriverManager();
            Statement statement = connection.createStatement();

            Logger logger = Logger.getAnonymousLogger();
            logger.setLevel(Level.OFF);

            loadAndRunScript("_data/sql/create.sql", statement, logger);
            loadAndRunScript("_data/sql/data.sql", statement, logger);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAndRunScript(String resourcePath, Statement stmt, Logger logger) throws SQLException, IOException {
        printMethodNameViaStackWalker(1);

        String sqlCreate = ClassLoader.getSystemResource(resourcePath).getFile();
        logger.log(Level.INFO, sqlCreate);
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(sqlCreate));

        String line = "";
        StringBuilder allScripts = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("--"))
                continue;
            allScripts.append(line);
        }

        String[] splitScript = allScripts.toString().split(";");
        for (String sss : splitScript) {
            logger.log(Level.INFO, sss);
            stmt.execute(sss);
        }

        System.out.println();
    }

    public static void main(String[] args) throws SQLException {
        printClassNameViaStackWalker(1);

        metadataViaDatabaseMetaData();
        metadataViaResultSetMetaData();

        establishConnectionViaDriverManager();
        establishConnectionViaDataSource();
        countRowsInResultSet();

        queryViaGeneralPurposeStatement();
        queryViaPreparedStatement();

        movingAroundViaCursor();
        //updateResultSet();

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

    }

    public static void establishConnectionViaDriverManager() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(
                "SELECT * FROM books " +
                        "WHERE book_id=909");

        while (rs.next()) {
            System.out.println(String.format("book_id:%d title:%s", rs.getInt("book_id"), rs.getString("title")));
        }

        System.out.println();
    }

    /*
     * This is typically used within a Java/Jee Middleware/Container
     * DataSource is obtained via JNDI lookup
     */
    public static void establishConnectionViaDataSource() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(
                "SELECT * FROM books " +
                        "WHERE book_id=909");

        while (rs.next()) {
            System.out.println(String.format("book_id:%d title:%s", rs.getInt("book_id"), rs.getString("title")));
        }

        System.out.println();
    }

    public static void queryViaGeneralPurposeStatement() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(
                "SELECT * FROM books " +
                        "WHERE book_id=909");

        while (rs.next()) {
            System.out.println(String.format("book_id:%d title:%s", rs.getInt("book_id"), rs.getString("title")));
        }

        System.out.println();

    }

    public static void queryViaPreparedStatement() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM books " +
                        "WHERE book_id=?"); // Notice the wildcard '?'

        statement.setInt(1, 909);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(String.format("book_id:%d title:%s", rs.getInt("book_id"), rs.getString("title")));
        }

        System.out.println();
    }

    /*
     * Used with Stored Procedures
     */
    public static void queryViaCallableStatements() {
        printMethodNameViaStackWalker(1);
    }

    public static void movingAroundViaCursor() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        Statement statement = connection.createStatement( // Must change the scroll type and specify the concurrency leve
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM books");

        rs.last();
        System.out.println(rs.getRow());

        rs.absolute(10);
        System.out.println(rs.getRow());

        rs.absolute(-1); // last row
        System.out.println(rs.getRow());

        rs.last();
        System.out.println(rs.getRow());

        rs.relative(-5); // move back 5 rows relative to current
        System.out.println(rs.getRow());

        rs.relative(0); // cursor remains where it is
        System.out.println(rs.getRow());

    }

    public static void metadataViaDatabaseMetaData() throws SQLException {

        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDriverName());
        System.out.println(databaseMetaData.getDefaultTransactionIsolation());

        System.out.println();
    }

    public static void metadataViaResultSetMetaData() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(
                "SELECT * FROM books " +
                        "WHERE book_id=909");

        ResultSetMetaData rsMetaData = rs.getMetaData();
        System.out.println(rsMetaData.getColumnCount());
        System.out.println(rsMetaData.getColumnClassName(1));

        // useful when the query is a join of two or more tables and we need to know which table a column came from
        System.out.println(rsMetaData.getTableName(1));
        System.out.println();
    }

    /**
     * Refer to {@link jdbc.metadata.CursorScrollableUpdatable}
     *
     * @throws SQLException
     */
    public static void fetchingResultSetWithSpecificCursor() throws SQLException {
        printMethodNameViaStackWalker(1);

        DerbyDB.getConnectionViaDriverManager().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
    }

    public static void countRowsInResultSet() throws SQLException {
        printMethodNameViaStackWalker(1);

        Statement statement = DerbyDB.getConnectionViaDriverManager().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = statement.executeQuery("SELECT * FROM books");

        if (rs.last()) { // move cursor to the very last row
            int rowNum = rs.getRow(); // get the row number
            System.out.println(String.format("Table Books has {%d} rows", rowNum));
            rs.beforeFirst(); // move cursor back to its original position before the 1st row
        }
        System.out.println();

        System.out.println("-- Alternatively, you can query the count via aggregate function: " +
                "'SELECT COUNT(*) AS C FROM books' ");
        ResultSet rs2 = statement.executeQuery("SELECT COUNT(*) as C FROM books");
        if (rs2.next())
            System.out.println(String.format("Table Books has {%d} rows", rs2.getInt("C")));

        System.out.println();

    }

    public static void updateResultSet() throws SQLException {
        printMethodNameViaStackWalker(1);

        Statement statement = DerbyDB.getConnectionViaDriverManager().createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, // Derby  doesn't support sensitive cursor
                ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = statement.executeQuery(
                "SELECT * FROM books " +
                        "WHERE book_id=909");

        if (rs.next()) { // move cursor to the very last row
            rs.updateString("title", "Book Title Has Been Changed");
            rs.updateRow();
        }

        ResultSet rs2 = DerbyDB.getConnectionViaDriverManager().createStatement().executeQuery(
                "SELECT * FROM books " +
                        "WHERE book_id=909");

        if (rs2.next()) {
            System.out.println(rs2.toString());
        }

    }


}


class JDBMetaData {
    public static void main(String[] args) throws SQLException {
        printClassNameViaStackWalker(1);

        printColumns();
    }

    public static void printColumns() throws SQLException {
        printMethodNameViaStackWalker(1);

        Connection connection = DerbyDB.getConnectionViaDriverManager();
        DatabaseMetaData metaData = connection.getMetaData();

        ResultSet columns = metaData.getColumns(null, null, "%", "%");

        while (columns.next()) {
            System.out.println(String.format("%s %s %s %s",
                    columns.getString("TABLE_NAME"),
                    columns.getString("COLUMN_NAME"),
                    columns.getString("TYPE_NAME"),
                    columns.getString("COLUMN_SIZE")));
        }
    }
}


class DerbyDB {
    private static final String URL_IN_MEMORY = "jdbc:derby:memory:bookdb;create=true";
    private static final String URL_FILE = URL_FILE();

    private static String URL_FILE() {
        String dbName = "bookdb";
        String path = System.getProperty("user.dir") + "/_out/" + dbName;

        return String.format("jdbc:derby:%s;create=true", path);
    }

    public static Connection getConnectionViaDriverManager() throws SQLException {
        return DriverManager.getConnection(URL_IN_MEMORY);
    }

    public static Connection getConnectionViaDataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Not needed since as of JDBC 4.0 Drivers will self-register with the DriverManager
//            dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl(URL_IN_MEMORY);
        return dataSource.getConnection();
    }

}

class A{
    void printMe(){
        System.out.println("A");
    }
}
class B extends A{
    @Override
    void printMe() {
        System.out.println("B");
    }
}

class C extends B{
    @Override
    void printMe() {
        System.out.println("C");
    }
}

class TT{
    public static void main(String[] args) {
        A a = new C();
        B b = (B)a;
        b.printMe();
    }
}
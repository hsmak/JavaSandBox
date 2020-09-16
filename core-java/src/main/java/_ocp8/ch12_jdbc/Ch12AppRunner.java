package _ocp8.ch12_jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static _ocp8.Utils.printClassNameViaStackWalker;
import static _ocp8.Utils.printMethodNameViaStackWalker;

class Ch12AppRunner {
    private static final String URL_DERBY = "jdbc:derby:memory:test_db;create=true";

    static {
        printMethodNameViaStackWalker(1);
        try {
            Connection connection = DriverManager.getConnection(URL_DERBY);
            Statement statement = connection.createStatement();

            Logger logger = Logger.getAnonymousLogger();
            logger.setLevel(Level.OFF);
            loadAndRunScript("_data/sql/create.sql", statement, logger);
            loadAndRunScript("_data/sql/data.sql", statement, logger);

            ResultSet rs = statement.executeQuery("SELECT * FROM books " +
                    "WHERE book_id=909");
            while (rs.next()) {
                System.out.println(String.format("book_id:%d title:%s", rs.getInt("book_id"), rs.getString("title")));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAndRunScript(String resourcePath, Statement stmt, Logger logger) throws SQLException, IOException {

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
    }

    public static void main(String[] args) throws SQLException {
        printClassNameViaStackWalker(1);

        Connection connection = DriverManager.getConnection(URL_DERBY);
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM books " +
                "WHERE book_id=909");
        while (rs.next()) {
            System.out.println(String.format("book_id:%d title:%s", rs.getInt("book_id"), rs.getString("title")));
        }

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

    public static void createPreparedStatements() {
        printMethodNameViaStackWalker(1);
    }

    public static void createCallableStatements() {
        printMethodNameViaStackWalker(1);
    }

    public static void fetchingResultSet() {
        printMethodNameViaStackWalker(1);
    }

}

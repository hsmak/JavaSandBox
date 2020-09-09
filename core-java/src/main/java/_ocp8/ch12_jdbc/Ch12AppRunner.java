package _ocp8.ch12_jdbc;

import java.sql.*;

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

        Connection connection = DriverManager.getConnection(URL_DERBY);
        Statement statement = connection.createStatement();
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

    public static void connectToRDBMS() {

    }

    public static void createStatements() {

    }

}

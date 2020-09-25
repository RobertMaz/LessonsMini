package JDBC_only;

import java.sql.*;

public class RollbackAndSavePoint {
    private static final String URL = "jdbc:mysql://localhost:3306/datas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();
            String createTable = "CREATE TABLE if not exists fruit (id integer primary key auto_increment, name varchar(10) not null, amount integer, price double)";
            String insert1 = "INSERT INTO fruit (name, amount, price) values ('Apple', 200, 3.5);";
            String insert2 = "INSERT INTO fruit (name, amount, price) values ('Orange', 50, 5.5);";
            String insert3 = "INSERT INTO fruit (name, amount, price) values ('Pineapple', 30, 9.5);";
            String insert4 = "INSERT INTO fruit (name, amount, price) values ('Lemon', 40, 3.4)";

//            connection.setAutoCommit(false);
//            statement.executeUpdate(createTable);
//            statement.executeUpdate(insert1);
//            statement.executeUpdate(insert2);
//            Savepoint savepoint = connection.setSavepoint();
//            statement.executeUpdate(insert3);
//            statement.executeUpdate(insert4);
//
//            connection.rollback(savepoint);
//
//            connection.commit();
//            connection.releaseSavepoint(savepoint);


            connection.setAutoCommit(true);
            statement.addBatch(createTable);
            statement.addBatch(insert1);
            statement.addBatch(insert2);
            statement.addBatch(insert3);
            statement.addBatch(insert4);
            statement.executeBatch();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
    }


    public static void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

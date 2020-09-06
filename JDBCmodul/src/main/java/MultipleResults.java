import java.sql.*;

public class MultipleResults {

    private static final String URL = "jdbc:mysql://localhost:3306/datas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static ResultSet resultSet;
    private static CallableStatement callableStatement;


    public static void main(String[] args) {
        try {
            connect();
            callableStatement = connection.prepareCall("{call tablesCount}");
            boolean hasResult = callableStatement.execute();
            while (hasResult) {
                resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    System.out.println("Count = " + resultSet.getInt(1));
                }
                hasResult = callableStatement.getMoreResults();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }


    public static void disconnect() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}

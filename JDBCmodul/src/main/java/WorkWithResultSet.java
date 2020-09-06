import java.sql.*;

public class WorkWithResultSet {

    private static final String URL = "jdbc:mysql://localhost:3306/datas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static ResultSet resultSet;
    private static Statement statement;


    public static void main(String[] args) {
        try {
            connect();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * FROM books");
//            while(resultSet.next()){
//                int id = resultSet.getInt(1);
//                double price = resultSet.getDouble(3);
//                if (id == 1) {
//                    resultSet.updateString("name", "inferno (discount)");
//                    resultSet.updateDouble(3, price - 10);
//                    resultSet.updateRow();
//                }
//            }

            if (resultSet.absolute(3)) {
                System.out.println(resultSet.getString("name"));
            }
            if (resultSet.previous()) {
                System.out.println(resultSet.getString("name"));
            }
            if (resultSet.last()) {
                System.out.println(resultSet.getString("name"));
            }
            if (resultSet.relative(-3)) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                while(resultSet.next()){
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String field = rsmd.getColumnName(i);
                        String value = resultSet.getString(field);
                        System.out.print(field + " : " + value + " ");
                    }
                    System.out.println();
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

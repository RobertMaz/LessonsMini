import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class RowSetLession {

    private static final String URL = "jdbc:mysql://localhost:3306/datas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static ResultSet resultSet;
    private static Statement statement;

    public static void main(String[] args) {
        try{
            ResultSet  rs = getResultSet();
//            while (rs.next()){
//                System.out.println(rs.getString("name"));
//            }
            CachedRowSet crs = (CachedRowSet)rs;
            crs.setCommand("SELECT * FROM books WHERE price > ?");
            crs.setDouble(1, 30);

            crs.setUrl(URL);
            crs.setUsername(USER_NAME);
            crs.setPassword(PASSWORD);
            crs.execute();

            while(crs.next()){
                String name = crs.getString("name");
                double price = crs.getDouble("price");
                System.out.println(name + " : " + price );
            }
            crs.absolute(3);
            crs.deleteRow();
            crs.beforeFirst();
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            crs.acceptChanges(connection);
            crs.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static ResultSet getResultSet() throws SQLException, ClassNotFoundException {
        try {
            connect();
            resultSet = statement.executeQuery("Select  * from books;");
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            CachedRowSet cachedRowSet = rowSetFactory.createCachedRowSet();
            cachedRowSet.populate(resultSet);
            return cachedRowSet;
        } finally {
            disconnect();
        }

    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
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

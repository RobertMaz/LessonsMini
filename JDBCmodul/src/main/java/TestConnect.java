
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class TestConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/datas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static ResultSet resultSet;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static CallableStatement callableStatement;


    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\MyComp\\IdeaProjects\\LessonsMini\\JDBCmodul\\src\\main\\resources\\books.sql"));
             Scanner scan = new Scanner(in)) {
            connect();
//            addLinesToDB(scan);
//            workWithPrepareStatement();
//            workWithResultSet(ps);
//            workWithCallableStatement();

            System.out.println("Connection successful");
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }

    private static void workWithCallableStatement() throws SQLException {
        callableStatement = connection.prepareCall("{call booksCount(?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();
        System.out.println("Count lines in table : " + callableStatement.getInt(1));
    }

    private static void workWithPrepareStatement() throws SQLException {
        preparedStatement = connection.prepareStatement("insert into books (name, price) values (?, ?)");
        preparedStatement.setString(1, "Schindler's list");
        preparedStatement.setDouble(2, 32.5);
        preparedStatement.execute();
    }


    private static void workWithResultSet(Statement statement) throws SQLException {
        resultSet = statement.executeQuery("select * from books");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            System.out.printf("id = %d, name = %s, price = %f\n", id, name, price);
        }
    }

    private static void addLinesToDB(Scanner scan) throws SQLException {
        String line = "";
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }
            statement.executeUpdate(line);
        }
    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
    }


    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
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
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (callableStatement != null){
                callableStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

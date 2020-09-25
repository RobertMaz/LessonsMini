package JDBC_only;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class BlobExample {

    private static final String URL = "jdbc:mysql://localhost:3306/datas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;


    public static void main(String[] args) {

        try {
            connect();
            statement.executeUpdate("CREATE TABLE if not exists images (name varchar(15), d date, image BLOB)");
            BufferedImage image = ImageIO.read(new File("C:\\Users\\MyComp\\IdeaProjects\\LessonsMini\\JDBCmodul\\src\\main\\resources\\photo.jpg"));
            Blob smile = connection.createBlob();
            try (OutputStream out = smile.setBinaryStream(1)) {
                ImageIO.write(image, "jpg", out);
            }
            preparedStatement = connection.prepareStatement("INSERT INTO images (name, d, image) values (?, {d ?}, ?)");
            preparedStatement.setString(1, "Photo");
            preparedStatement.setDate(2, Date.valueOf("2020-09-06"));
            preparedStatement.setBlob(3, smile);
            preparedStatement.execute();

            resultSet = preparedStatement.executeQuery("select * from images");
            while(resultSet.next()){
                Blob newSmile = resultSet.getBlob("image");
                BufferedImage image1 = ImageIO.read(newSmile.getBinaryStream());
                File out = new File("saved.jpg");
                ImageIO.write(image1, "jpg", out);
                
            }

        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        finally {
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
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

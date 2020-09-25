package allClasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBConnection {
    private Connection connection;

    public UserDBConnection(Connection connection) throws SQLException {
        this.connection = connection;
        createDBIfNotExist();
    }


    public List<User> select() {
        List<User> users = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet resultSet = st.executeQuery("select * from users;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String city = resultSet.getString(3);
                int age = resultSet.getInt(4);
                users.add(new User(id, name, age, city));
            }
            return users;
        }catch (SQLException e ){

        }
        throw new RuntimeException();
    }

    public void insert(User user) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into users (name, city, age) values (?, ?, ?)"
        )) {
            if (user.getName() != null && !user.getName().isEmpty() &&
                    user.getCity() != null && !user.getCity().isEmpty()) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getCity());
                ps.setInt(3, user.getAge());
                ps.execute();
            }
        }
    }

    private void createDBIfNotExist() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists users " +
                    "(id integer auto_increment primary key , name varchar(20), city varchar(20), age integer);");

        }
    }
}

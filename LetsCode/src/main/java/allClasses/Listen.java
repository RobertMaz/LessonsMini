package allClasses;

import allClasses.UserDBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class Listen implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext config = sce.getServletContext();
        String jdbcConnectionString = config.getInitParameter("jdbcConnectionString");
        String username = config.getInitParameter("username");
        String password = config.getInitParameter("password");

        try {
            Connection connection = DriverManager.getConnection(jdbcConnectionString, username, password);
            UserDBConnection userDBConnection = new UserDBConnection(connection);
            config.setAttribute("connection", connection);
            config.setAttribute("users", userDBConnection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Connection connection = (Connection) sc.getAttribute("connection");
        try {
            if (connection != null && connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

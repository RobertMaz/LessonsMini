package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<head><title>Main Page</title></head>\n");
        resp.getWriter().println("<p>This is Main page </p>");
        resp.getWriter().println("<ul>\n" +
                "  <p><li><a href=\"main\">Home</a></li></p>\n" +
                "  <p><li><a href=\"order\">Order</a></li></p>\n" +
                "  <p><li><a href=\"catalog\">Catalog</a></li></p>\n" +
                "  <p><li><a href=\"product\">Products</a></li></p>\n" +
                "  <p><li><a href=\"cartServlet\">Cart</a></li></p>\n" +
                "</ul>");


    }
}

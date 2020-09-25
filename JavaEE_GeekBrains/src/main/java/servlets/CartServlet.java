package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = "/cartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<head><title>Cart</title></head>\n");
        resp.getWriter().println("<p>This is Cart </p>");
        resp.getWriter().println("<ul>\n" +
                "  <li><a href=\"main\">Home</a></li>\n" +
                "  <li><a href=\"order\">Order</a></li>\n" +
                "  <li><a href=\"catalog\">Catalog</a></li>\n" +
                "  <li><a href=\"product\">Products</a></li>\n" +
                "  <li><a href=\"cartServlet\">Cart</a></li>\n" +
                "</ul>");
    }
}

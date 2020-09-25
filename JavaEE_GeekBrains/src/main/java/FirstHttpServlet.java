import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "FirstHttpServlet", urlPatterns = "/FirstHttpServlet/*")
public class FirstHttpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<p>Привет from Http servlet </p>");
        resp.getWriter().println("<p>req.getContextPath() = " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>req.getQueryString() = " + req.getQueryString()+ "</p>");
        req.getParameterMap().forEach((key, val)->
        {
            try {
                resp.getWriter().println("<p>" + key + " : " + Arrays.toString(val)+ "</p>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resp.getWriter().println("<head><title>FirstServletTitle</title></head>\n");
        resp.getWriter().println("req.getMethod() = " + req.getMethod()+ "</p>");


    }
}

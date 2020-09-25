import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletConfig = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<p>Hello from servlet!!</p>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

package allClasses;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/mainPage"})
public class MainServlet extends HttpServlet {
    private UserDBConnection users;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users = (UserDBConnection) getServletContext().getAttribute("users");
        if (users == null){
            throw new ServletException("allClasses.UserDBConnection is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello is main page");
        String id = req.getParameter("personId").trim();
        String token = "https://api.vk.com/method/users.get?" +
                "user_ids=" + id + "&fields=bdate&" +
                "access_token=f0b32d48f0b32d48f0b32d48bbf0c1dfbbff0b3f0b32d48afb653a45a6b2197d24454e6&v=5.124";
        try {
            PrintWriter writer = resp.getWriter();
            HttpResponse <JsonNode>  response = Unirest.get(token).asJson();
            ObjectMapper objectMapper = new ObjectMapper();
            VKUser vkUser = objectMapper.readValue(response.getStatusText(), VKUser.class);
            writer.println(vkUser);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("userName");
        String city = req.getParameter("userCity");
        int age = Integer.parseInt(req.getParameter("userAge"));
        try {
            users.insert(new User(-1, name, age, city));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/LetsCode/");

    }
}

package servlet;

import entity.User;
import service.ServiceUser;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class SignupServlet extends HttpServlet {

    private final TemplateEngine engine;
    private final ServiceUser service = new ServiceUser();

    public SignupServlet(TemplateEngine engine){
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        engine.render("signup.ftl",new HashMap<>(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String imageUrl = req.getParameter("imageUrl");
        String job = req.getParameter("job");
        service.saveUser(name, surname, email, password, imageUrl, job);
        resp.sendRedirect("/login");
    }
}

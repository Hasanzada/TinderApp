package servlet;

import entity.User;
import service.CookieService;
import service.ServiceUser;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {

    private final TemplateEngine engine;
    private ServiceUser service = new ServiceUser();
    private CookieService cookieService;

    public LoginServlet(TemplateEngine engine){
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        engine.render("login.ftl", new HashMap<>(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int id = service.getUserByEmailAndPassword(email, password).getId();
        cookieService = new CookieService(req, resp);
        cookieService.addCookie(id);
        resp.sendRedirect("/users");
    }
}

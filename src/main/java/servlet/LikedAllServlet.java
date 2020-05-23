package servlet;

import entity.User;
import service.CookieService;
import service.ServiceUser;
import utils.TemplateEngine;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class LikedAllServlet extends HttpServlet {

    private final TemplateEngine engine;
    private  ServiceUser serviceUser = new ServiceUser();
    private CookieService cookieService;

    public LikedAllServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        cookieService = new CookieService(req, resp);
        String activeUserId = cookieService.getCookie().map(Cookie::getValue).orElseThrow(() -> new RuntimeException("Exception, no cookie"));
        List<User> list = serviceUser.getAllLikedUsers(activeUserId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("list", list);
        engine.render("people-list.ftl", data, resp);
    }
}

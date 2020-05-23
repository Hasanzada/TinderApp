package servlet;

import service.CookieService;
import service.ServiceLike;
import service.ServiceUser;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {

    private final TemplateEngine engine;
    private CookieService cookieService;
    private ServiceUser serviceUser = new ServiceUser();
    private ServiceLike serviceLike = new ServiceLike();

    public UsersServlet(TemplateEngine engine){
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         cookieService = new CookieService(req, resp);
         int id = getActiveUserID();
        if(serviceUser.getNext(id).isEmpty())
            resp.sendRedirect("/likedall");
         serviceUser.getNext(id).ifPresent(user -> {
             HashMap<String, Object> data = new HashMap<>();
             data.put("user", user);
             engine.render("like-page.ftl", data, resp);
         });

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String likedUserId = req.getParameter("id");
        String isLiked = req.getParameter("is_liked");
        cookieService = new CookieService(req, resp);
        int activeUserId = getActiveUserID();
        serviceLike.save(activeUserId, likedUserId, isLiked);
        resp.sendRedirect("/users");

    }

    private int getActiveUserID(){
        String activeUserId = cookieService
                .getCookie()
                .map(Cookie::getValue)
                .orElseThrow(() -> new RuntimeException("Exception, no cookie"));
        return Integer.parseInt(activeUserId);
    }
}

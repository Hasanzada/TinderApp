package servlet;

import service.CookieService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private CookieService cookieService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        cookieService = new CookieService(req, resp);
        cookieService.removeCookie();
        resp.sendRedirect("/login");
    }
}

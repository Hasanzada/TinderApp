package filter;

import entity.User;
import service.ServiceUser;
import utils.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class SignupFilter implements Filter {

    private final ServiceUser serviceUser = new ServiceUser();
    private final TemplateEngine engine;

    public SignupFilter(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    private boolean isUserExist(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(email, password);
        return serviceUser.checkUserByEmailAndPassword(user);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getMethod().equals("POST")) {
            if (isUserExist(req)) {
                engine.render("signup_error.ftl", new HashMap<>(), resp);
            } else
                filterChain.doFilter(servletRequest, servletResponse);

        } else
            filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}

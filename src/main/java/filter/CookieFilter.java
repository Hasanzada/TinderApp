package filter;

import service.CookieService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements Filter {

    private CookieService cookieService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!isHttp(servletRequest, servletResponse))
            throw new IllegalArgumentException("Servlet Request and Response should be instance of HttpServlet");
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (!isRequestValid((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)
                && !isURI((HttpServletRequest) servletRequest)
        )resp.sendRedirect("/login");
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    private boolean isURI(HttpServletRequest req) {
        return req.getRequestURI().matches("/login|/signup");
    }

    private boolean isRequestValid(HttpServletRequest req, HttpServletResponse resp) {
        cookieService = new CookieService(req, resp);
        return cookieService.getCookie().isPresent();
    }

    private boolean isHttp(ServletRequest req, ServletResponse resp) {
        return req instanceof HttpServletRequest && resp instanceof HttpServletResponse;
    }

    @Override
    public void destroy() {

    }
}

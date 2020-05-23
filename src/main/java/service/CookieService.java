package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {
    private final String COOKIE_NAME = "activeUser";
    private HttpServletRequest req;

    private HttpServletResponse resp;

    public CookieService(HttpServletRequest req, HttpServletResponse resp){
        this.req = req;
        this.resp = resp;
    }

    public Optional<Cookie> getCookie(){
        Optional<Cookie> result = Optional.empty();
        Cookie[] cookies = req.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie c : cookies){
                if(c.getName().equals(COOKIE_NAME)){
                    result = Optional.of(c);
                }
            }
        }
        return result;
    }

    public void addCookie(int id){
        resp.addCookie(new Cookie(COOKIE_NAME, String.valueOf(id)));
    }

    public void removeCookie(){
        Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equalsIgnoreCase(COOKIE_NAME))
                .map(c -> new Cookie(c.getName(),c.getValue()){{
                    setMaxAge(0);
                }}).forEach(resp::addCookie);
    }

}

package servlet;

import entity.Chat;
import entity.User;
import service.CookieService;
import service.ServiceChat;
import service.ServiceUser;
import utils.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class ChatServlet extends HttpServlet {

    private final TemplateEngine engine;
    private CookieService cookieService;
    private ServiceUser serviceUser = new ServiceUser();
    private ServiceChat serviceChat = new ServiceChat();

    public ChatServlet(TemplateEngine engine){
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        cookieService = new CookieService(req, resp);
        String activeUserIds = cookieService.getCookie().map(Cookie::getValue).orElseThrow(() -> new RuntimeException("Exception, something is wrong with cookie"));
        int activeUserId = Integer.parseInt(activeUserIds);
        int likedUserId = Integer.parseInt(req.getParameter("id"));
        Chat chat = new Chat(activeUserId, likedUserId);
        List<Chat> chats = serviceChat.getAllChat(chat);

        User activeUser;
        User likedUser;
        if(serviceUser.getUserById(activeUserId).isPresent()) {
            activeUser = serviceUser.getUserById(activeUserId).get();
        } else throw new RuntimeException("There is no user, something is wrong");
        if(serviceUser.getUserById(likedUserId).isPresent()) {
            likedUser = serviceUser.getUserById(likedUserId).get();
        } else throw new RuntimeException("There is no user, something is wrong");

        HashMap<String, Object> data = new HashMap<>();
        data.put("activeUser", activeUser);
        data.put("likedUser", likedUser);
        data.put("chats", chats);
        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm /ddMMM");
        String formattedString = now.format(formatter);
        String text = req.getParameter("text");
        cookieService = new CookieService(req, resp);
        String activeUserId = cookieService.getCookie().map(Cookie::getValue).orElseThrow(() -> new RuntimeException("Exception, no cookie"));
        int id = Integer.parseInt(req.getParameter("id"));
        Chat chat = new Chat(Integer.parseInt(activeUserId), id, text, formattedString);
        serviceChat.saveChat(chat);
        resp.sendRedirect("/chat?id="+id);

    }
}

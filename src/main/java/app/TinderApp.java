package app;

import db.ConnDetails;
import db.DbSetup;
import filter.CookieFilter;
import filter.LoginFilter;
import filter.SignupFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;
import utils.TemplateEngine;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {
        DbSetup.migrate(ConnDetails.URL, ConnDetails.USERNAME, ConnDetails.PASSWORD);
        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();

        TemplateEngine engine = TemplateEngine.folder("./content/templates");
        handler.addServlet(LogoutServlet.class,"/logout");
        handler.addServlet(new ServletHolder(new SignupServlet(engine)),"/signup");
        handler.addServlet(new ServletHolder(new LoginServlet(engine)),"/login");
        handler.addServlet(new ServletHolder(new UsersServlet(engine)),"/users");
        handler.addServlet(new ServletHolder(new LikedAllServlet(engine)),"/likedall");
        handler.addServlet(new ServletHolder(new ChatServlet(engine)),"/chat");

        handler.addFilter(CookieFilter.class,"/*", ft);
        handler.addFilter(new FilterHolder(new LoginFilter(engine)),"/login", ft);
        handler.addFilter(new FilterHolder(new SignupFilter(engine)),"/signup", ft);

        server.setHandler(handler);
        server.start();
        server.join();


    }
}

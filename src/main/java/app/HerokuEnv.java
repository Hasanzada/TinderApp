package app;

public class HerokuEnv {

    private final static IllegalArgumentException empty_url = new IllegalArgumentException("JDBC_DATABASE_URL is empty");
    private final static IllegalArgumentException empty_username = new IllegalArgumentException("JDBC_DATABASE_USERNAME is empty");
    private final static IllegalArgumentException empty_password = new IllegalArgumentException("JDBC_DATABASE_PASSWORD is empty");

    public static int port() {
        try {
            String port = System.getenv("PORT");
            return Integer.parseInt(port);
        } catch (NumberFormatException ex) {
            return 5000;
        }
    }

    public static String jdbc_url(){
        String url = System.getenv("JDBC_DATABASE_URL");
        if(url == null) throw empty_url;
        return url;
    }

    public static String jdbc_user(){
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        if(username == null) throw empty_username;
        return username;
    }

    public static String jdbc_password(){
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        if(password == null) throw empty_password;
        return password;
    }

}

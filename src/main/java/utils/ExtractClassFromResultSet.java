package utils;

import entity.Chat;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtractClassFromResultSet {

    public static User extractUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String imageUrl = rs.getString("imgurl");
        String job = rs.getString("job");
        return new User(id, email, password, name, surname, imageUrl, job);
    }

    public static Chat extractChat(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int userFromId = rs.getInt("user_from_id");
        int userToId = rs.getInt("user_to_id");
        String messageText = rs.getString("message_text");
        String messageTime = rs.getString("message_time");
        return new Chat(id, userFromId, userToId, messageText, messageTime);
    }
}

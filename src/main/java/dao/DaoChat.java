package dao;

import db.DbConn;
import entity.Chat;
import entity.User;
import utils.ExtractClassFromResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DaoChat extends DaoAbstract<Chat> {

    private final String SQL_GET_ALL = "SELECT * FROM chat ";

    private final String SQL_SAVE = "insert into chat (user_from_id, user_to_id, message_text, message_time)" +
            " values(?,?,?,?)";


    @Override
    public Collection<Chat> getAll() {
        try (Connection conn = DbConn.connect()){
            List<Chat> chats = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                chats.add(ExtractClassFromResultSet.extractChat(rs));
            }
            return chats;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Chat> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Chat chat) {
        try (Connection conn = DbConn.connect()) {
            PreparedStatement ps = conn.prepareStatement(SQL_SAVE);
            ps.setInt(1, chat.getUserFromId());
            ps.setInt(2, chat.getUserToId());
            ps.setString(3, chat.getMessageText());
            ps.setString(4, chat.getMessageTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }


}

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
import java.util.List;
import java.util.Optional;

public abstract class DaoAbstract<A> implements DAO<A> {

    public List<User> getAll(int id) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbConn.connect()) {
            String SQL = "SELECT * FROM user WHERE id IN " +
                    "(SELECT liked_user_id FROM likes WHERE user_id = ? AND STATUS = 1);";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(ExtractClassFromResultSet.extractUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public Optional<User> getNext(int id) {
        try (Connection conn = DbConn.connect()) {
            String SQL = "SELECT * FROM user " +
                    "WHERE id NOT IN (SELECT liked_user_id FROM likes WHERE user_id = ?) AND id <> ? " +
                    "LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(ExtractClassFromResultSet.extractUser(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
        return Optional.empty();
    }

    public List<Chat> getAll(Chat chat) {
        List<Chat> messages = new ArrayList<>();
        String SQL_GET_ALL = "SELECT * FROM chat " +
                "WHERE user_from_id IN (?, ?) AND user_to_id IN (?, ?)";
        try(Connection conn = DbConn.connect()){
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL);
            ps.setInt(1, chat.getUserFromId());
            ps.setInt(2, chat.getUserToId());
            ps.setInt(3, chat.getUserFromId());
            ps.setInt(4, chat.getUserToId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                messages.add(ExtractClassFromResultSet.extractChat(rs));
            }
            rs.close();
            ps.close();
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public Optional<User> get(User user) {
        String sql = "select * from user where email = ? and password = ?";
        try(Connection conn = DbConn.connect()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(ExtractClassFromResultSet.extractUser(rs));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
        return Optional.empty();
    }


}

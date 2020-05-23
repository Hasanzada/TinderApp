package dao;

import db.DbConn;
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

public class DaoUser extends DaoAbstract<User> {

    private final String SQL_ALL = "select * from user";


    @Override
    public Collection<User> getAll() {
        try (Connection conn = DbConn.connect()){
            List<User> users = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(SQL_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                users.add(ExtractClassFromResultSet.extractUser(rs));
            }
            return users;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /*@Override
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
    }*/


    @Override
    public Optional<User> getById(int id) {
        try(Connection conn = DbConn.connect()){
            String SQL = "select * from user where id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Optional.of(ExtractClassFromResultSet.extractUser(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
        return Optional.empty();

    }

    //@Override
    /*public Optional<User> getNext(int id) {
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
    }*/

    @Override
    public void create(User user) {
        try (Connection conn = DbConn.connect()){
            String SQL = "insert into user" +
                    "(name, surname, imgurl, email, password, last_login, job) " +
                    "values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getImageUrl());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getLastLogin());
            ps.setString(7, user.getJob());
            ps.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException("Error save user");
        }
    }

    /*private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String imageUrl = rs.getString("imgurl");
        String lastLogin = rs.getString("last_login");
        String job = rs.getString("job");
        return new User(id, email, password, name, surname, imageUrl, lastLogin, job);
    }*/
}

package dao;

import db.DbConn;
import entity.User;
import utils.ExtractClassFromResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    public void create(User user) {
        try (Connection conn = DbConn.connect()){
            String SQL = "insert into user" +
                    "(name, surname, imgurl, email, password, job) " +
                    "values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getImageUrl());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getJob());
            ps.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException("Error save user");
        }
    }

}

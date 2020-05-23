package dao;

import db.DbConn;
import entity.Likes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DaoLikes extends DaoAbstract<Likes> {

    private final String SQL_SAVE = "INSERT INTO likes (user_id, liked_user_id, STATUS) VALUES (?, ?, ?)";


    @Override
    public Collection<Likes> getAll() {
        return null;
    }

    @Override
    public Optional<Likes> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void create(Likes likes) {
        try (Connection conn = DbConn.connect()){
            PreparedStatement ps = conn.prepareStatement(SQL_SAVE);
            ps.setInt(1, likes.getUserId());
            ps.setInt(2, likes.getSelectedUserId());
            ps.setBoolean(3, likes.isLiked());
            ps.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException("Error save user");
        }
    }

}

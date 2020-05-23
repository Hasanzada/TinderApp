package service;

import dao.DAO;
import dao.DaoAbstract;
import dao.DaoLikes;
import entity.Likes;

public class ServiceLike {
    DaoAbstract dao = new DaoLikes();

    public void save(int activeUserIds, String likedUserIds, String isLikeds) {
        //int activeUserId = Integer.parseInt(activeUserIds);
        int likedUserId = Integer.parseInt(likedUserIds);
        boolean isLiked = Boolean.parseBoolean(isLikeds);
        Likes like = new Likes(activeUserIds, likedUserId, isLiked);
        dao.create(like);
    }
}

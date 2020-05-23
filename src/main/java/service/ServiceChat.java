package service;

import dao.DAO;
import dao.DaoAbstract;
import dao.DaoChat;
import entity.Chat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceChat {

    DaoAbstract dao = new DaoChat();

    public void saveChat(Chat chat){
        dao.create(chat);
    }

    public Collection<Chat> getAll() {
        return dao.getAll();
    }

    public List<Chat> getAllChat(Chat chat) {
        return dao.getAll(chat);
    }


}

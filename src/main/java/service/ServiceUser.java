package service;


import dao.DaoAbstract;
import dao.DaoUser;
import entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ServiceUser {

    DaoAbstract dao = new DaoUser();

    public Collection<User> getAllUsers(){
        return dao.getAll();
    }


    public Optional<User> getNext(int id) {
        return dao.getNext(id);
    }

    public List<User> getAllLikedUsers(String activeUserIds) {
        int activeUserId = Integer.parseInt(activeUserIds);
        return dao.getAll(activeUserId);
    }

    public void saveUser(String name, String surname, String email, String password, String imageUrl, String job){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setImageUrl(imageUrl);
        user.setPassword(password);
        user.setJob(job);
        dao.create(user);
    }

    public Optional<User> getUserById(int id) {
        return dao.getById(id);
    }

    public boolean checkUserByEmailAndPassword(User user) {
        return dao.get(user).isPresent();
    }

    public User getUserByEmailAndPassword(String email, String password){
        /*return getAllUsers().stream()
                .filter(f -> f.getEmail().equals(email) && f.getPassword().equals(password))
                .findFirst().orElseThrow();*/
        return (User) dao.get(new User(email,password)).get();
    }
}

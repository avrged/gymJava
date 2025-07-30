package repositories.interfaces;

import entities.users.User;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepoInt {
    boolean addUser(User user);
    boolean removeUser(int id);
    Optional<User> findById(int id);
    ArrayList<User> findAllUsers();
    ArrayList<User> findByType(Class<? extends User> userType);
}

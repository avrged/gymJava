package app.users;

import entities.users.User;
import repositories.interfaces.UserRepoInt;

import java.util.ArrayList;
import java.util.Optional;

public class UserRepo implements UserRepoInt {
    private final ArrayList<User> users;
    private int nextId = 1;

    public UserRepo() {
        this.users = new ArrayList<>();
    }

    @Override
    public boolean addUser(User user) {
        if (user.getId() == 0) {
            user.setId(nextId++);
        }
        users.add(user);
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        int initialSize = users.size();
        users.removeIf(user -> user.getId() == id);
        return users.size() < initialSize;
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public ArrayList<User> findAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public ArrayList<User> findByType(Class<? extends User> userType) {
        ArrayList<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (userType.isInstance(user)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
}

package app.users;

import entities.users.Instructor;
import entities.users.Subscriber;
import entities.users.User;
import repositories.interfaces.UserRepoInt;

import java.util.ArrayList;

public class UserService {
    private final UserRepoInt userRepository;

    public UserService(UserRepoInt userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public boolean removeUser(int id) {
        return userRepository.removeUser(id);
    }

    public void listSubscribers() {
        ArrayList<User> subscribers = userRepository.findByType(Subscriber.class);
        if (subscribers.isEmpty()) {
            System.out.println("No hay suscriptores registrados.");
        } else {
            for (User user : subscribers) {
                System.out.println(user);
            }
        }
    }

    public void listInstructors() {
        ArrayList<User> instructors = userRepository.findByType(Instructor.class);
        if (instructors.isEmpty()) {
            System.out.println("No hay instructores registrados.");
        } else {
            for (User user : instructors) {
                System.out.println(user);
            }
        }
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
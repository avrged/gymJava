import app.courses.CourseRepo;
import app.courses.CourseService;
import app.users.UserRepo;
import app.users.UserService;
import app.users.SubscriberDBRepo;
import app.users.SubscriberDBService;
import utils.Menu;

public class Main {

    public static void main(String[] args) {

        UserRepo userRepository = new UserRepo();
        CourseRepo courseRepository = new CourseRepo();
        UserService userService = new UserService(userRepository);
        CourseService courseService = new CourseService(courseRepository, userRepository);

        SubscriberDBRepo subscriberDBRepository = new SubscriberDBRepo();
        SubscriberDBService subscriberDBService = new SubscriberDBService(subscriberDBRepository);

        Menu menu = new Menu(userService, courseService, subscriberDBService);
        menu.startMenu();
    }
}
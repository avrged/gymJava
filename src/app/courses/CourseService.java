package app.courses;

import entities.courses.Course;
import entities.users.Instructor;
import entities.users.Subscriber;
import entities.users.User;
import repositories.interfaces.CourseRepoInt;
import repositories.interfaces.UserRepoInt;

public class CourseService {
    private final CourseRepoInt courseRepository;
    private final UserRepoInt userRepository;

    public CourseService(CourseRepoInt courseRepository, UserRepoInt userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public boolean addCourse(String name, String period, String description, int instructorId) {
        User user = userRepository.findById(instructorId).orElse(null);
        if (!(user instanceof Instructor instructor)) {
            return false;
        }

        int courseId = ((CourseRepo) courseRepository).getNextId();
        Course course = new Course(courseId, period, name, description, instructor);
        return courseRepository.addCourse(course);
    }

    public boolean removeCourse(int courseNo) {
        return courseRepository.removeCourse(courseNo);
    }

    public boolean addSubscriber(int courseNo, int subscriberId) {
        Course course = findCourseById(courseNo);
        if (course == null) {
            return false;
        }

        User user = userRepository.findById(subscriberId).orElse(null);
        if (!(user instanceof Subscriber subscriber)) {
            return false;
        }

        return course.addSubscriber(subscriber);
    }

    public boolean removeSubscriber(int courseNo, int subscriberId) {
        Course course = findCourseById(courseNo);
        if (course == null) {
            return false;
        }

        User user = userRepository.findById(subscriberId).orElse(null);
        if (!(user instanceof Subscriber subscriber)) {
            return false;
        }

        return course.removeSubscriber(subscriber);
    }

    public Course findCourseById(int courseNo) {
        return courseRepository.findById(courseNo).orElse(null);
    }

    public void listAllCourses() {
        var courses = courseRepository.findAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
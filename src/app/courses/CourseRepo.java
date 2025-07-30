package app.courses;

import app.users.UserRepo;
import entities.courses.Course;
import repositories.interfaces.CourseRepoInt;

import java.util.ArrayList;
import java.util.Optional;

public class CourseRepo implements CourseRepoInt {
    private final ArrayList<Course> courses;
    private int nextId = 1;

    public CourseRepo() {
        this.courses = new ArrayList<>();
    }

    @Override
    public boolean addCourse(Course course) {
        if (course.getCourseNo() == 0) {
            course.setCourseNo(getNextId());
        }
        courses.add(course);
        return true;
    }

    @Override
    public boolean removeCourse(int courseNo) {
        int initialSize = courses.size();
        courses.removeIf(course -> course.getCourseNo() == courseNo);
        return courses.size() < initialSize;
    }

    @Override
    public Optional<Course> findById(int courseNo) {
        return courses.stream()
                .filter(course -> course.getCourseNo() == courseNo)
                .findFirst();
    }

    @Override
    public ArrayList<Course> findAllCourses() {
        return new ArrayList<>(courses);
    }

    public int getNextId() {
        return nextId++;
    }
}

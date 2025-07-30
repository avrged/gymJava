package repositories.interfaces;

import entities.courses.Course;

import java.util.ArrayList;
import java.util.Optional;

public interface CourseRepoInt {
    boolean addCourse(Course course);
    boolean removeCourse(int courseNo);
    Optional<Course> findById(int courseNo);
    ArrayList<Course> findAllCourses();
}

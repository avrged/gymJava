package entities.courses;

import entities.users.Instructor;
import entities.users.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseNo;
    private String coursePeriod;
    private String courseName;
    private String courseDescription;
    private Instructor instructor;
    private final ArrayList<Subscriber> subscribers = new ArrayList<>();

    public Course(int courseNo, String coursePeriod, String courseName, String courseDescription, Instructor instructor) {
        this.courseNo = courseNo;
        this.coursePeriod = coursePeriod;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.instructor = instructor;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(String coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean addSubscriber(Subscriber subscriber) {
        return subscribers.add(subscriber);
    }

    public boolean removeSubscriber(Subscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    public List<Subscriber> getSubscribers() {
        return new ArrayList<>(subscribers);
    }

    public boolean hasSubscribers() {
        return !subscribers.isEmpty();
    }

    @Override
    public String toString() {
        return "Curso #" + courseNo + ": " + courseName +
                " (" + coursePeriod + "), Instructor: " +
                (instructor != null ? instructor.getNombre() : "Ninguno") +
                ", Suscriptores: " + subscribers.size();
    }
}
package be.abis.springsoapexercise.repository;

import be.abis.springsoapexercise.exception.CourseNotFoundException;
import be.abis.springsoapexercise.ws.model.Course;

import java.util.List;

public interface CourseRepository {
    Course findCourseByShortTitle(String title) throws CourseNotFoundException;
    List<Course> findCourseByNumberOfDays(int numberOfDays);
}

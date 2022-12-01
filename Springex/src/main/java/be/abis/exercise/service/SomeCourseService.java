package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Qualifier("some")
public class SomeCourseService implements CourseService{
    @Override
    public List<Course> findAllCourse() {
        return null;
    }

    @Override
    public Course findCourse(int id) {
        return new Course("7900","hey","hey you",8,50);
    }

    @Override
    public Course findCourse(String title) {
        return null;
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void updateCourse(Course c) {

    }

    @Override
    public void deleteCourse(int id) {

    }

    @Override
    public List<Course> getCourseListUnder500() {
        return null;
    }
}

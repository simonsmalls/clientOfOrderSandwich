package be.abis.springsoapexercise.repository;

import be.abis.springsoapexercise.exception.CourseNotFoundException;
import be.abis.springsoapexercise.ws.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class MemoryCourseRepository implements  CourseRepository{


    List<Course> courses=new ArrayList<>();


    public MemoryCourseRepository() {
        Course c1= new Course("1","java","java adv",5,50);
        Course c2= new Course("2","python","python adv",7,50);
        Course c3= new Course("3","javascript","javascript adv",5,30);
        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
    }

    @Override
    public Course findCourseByShortTitle(String title) throws CourseNotFoundException {
        return courses.stream().filter(x->x.getShortTitle().equals(title))
                .findFirst().orElseThrow(()->new CourseNotFoundException("course not found"));
    }

    @Override
    public List<Course> findCourseByNumberOfDays(int numberOfDays) {
        return courses.stream().filter(x->x.getNumberOfDays()==numberOfDays).collect(Collectors.toList());
    }
}

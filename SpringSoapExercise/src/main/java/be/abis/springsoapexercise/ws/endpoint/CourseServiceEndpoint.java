package be.abis.springsoapexercise.ws.endpoint;

import be.abis.springsoapexercise.exception.CourseNotFoundException;
import be.abis.springsoapexercise.repository.CourseRepository;
import be.abis.springsoapexercise.ws.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseServiceEndpoint {
    final static String NAMESPACE_URI="http://abis.be/courses";

    @Autowired
    ObjectFactory objectFactory;

    @Autowired
    CourseRepository courseRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "courseByShortTitleRequest")
    @ResponsePayload
    public CourseByShortTitleResponse findCourseByShortTitle
            (@RequestPayload CourseByShortTitleRequest courseByShortTitleRequest) throws CourseNotFoundException {
        String title = courseByShortTitleRequest.getShortTitle();
        CourseByShortTitleResponse courseByShortTitleResponse =
                objectFactory.createCourseByShortTitleResponse();
        courseByShortTitleResponse.setCourse(courseRepository.findCourseByShortTitle(title));
        return courseByShortTitleResponse;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "coursesByNumberOfDaysRequest")
    @ResponsePayload
    public CoursesByNumberOfDaysResponse findCoursesByNumberOfDays(@RequestPayload CoursesByNumberOfDaysRequest coursesByNumberOfDaysResquest){

        CoursesByNumberOfDaysResponse coursesByNumberOfDaysResponse =
                objectFactory.createCoursesByNumberOfDaysResponse();
        int days = coursesByNumberOfDaysResquest.getNumberOfDays();
        List<Course> courses = courseRepository.findCourseByNumberOfDays(days);
        Courses coursesBydays = new Courses();
        coursesBydays.setCourses(courses);
        coursesByNumberOfDaysResponse.setCourses(coursesBydays);
        return coursesByNumberOfDaysResponse;
    }
}

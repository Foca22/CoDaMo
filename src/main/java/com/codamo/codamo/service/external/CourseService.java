package com.codamo.codamo.service.external;

import com.codamo.codamo.dto.exceptions.course.CourseNotFoundException;
import com.codamo.codamo.dto.course.request.CreateCourseRequest;
import com.codamo.codamo.dto.course.request.UpdateCourseRequest;
import com.codamo.codamo.dto.course.response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CreateCourseRequest createCourseRequest);

    CourseResponse getCourse(String id) throws CourseNotFoundException;

    List<CourseResponse> getAllCourses();

    CourseResponse updateCourse(UpdateCourseRequest updateCourseRequest) throws CourseNotFoundException;

    void deleteCourse(String id) throws CourseNotFoundException;
}

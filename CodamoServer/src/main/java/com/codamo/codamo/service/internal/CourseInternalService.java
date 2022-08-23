package com.codamo.codamo.service.internal;

import com.codamo.codamo.dto.exceptions.course.CourseNotFoundException;
import com.codamo.codamo.dto.course.response.CourseResponse;
import com.codamo.codamo.model.course.Course;
import com.codamo.codamo.model.course.Course;

public interface CourseInternalService {
    Course findCourseById(String id) throws CourseNotFoundException;

    CourseResponse toCourseResponse(Course project);
}

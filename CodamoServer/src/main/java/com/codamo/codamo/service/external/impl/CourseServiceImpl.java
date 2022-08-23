package com.codamo.codamo.service.external.impl;


import com.codamo.codamo.dto.exceptions.course.CourseNotFoundException;
import com.codamo.codamo.dto.course.request.CreateCourseRequest;
import com.codamo.codamo.dto.course.request.UpdateCourseRequest;
import com.codamo.codamo.dto.course.response.CourseResponse;
import com.codamo.codamo.model.course.Course;
import com.codamo.codamo.repo.CourseRepo;
import com.codamo.codamo.service.external.CourseService;
import com.codamo.codamo.service.internal.CourseInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private CourseInternalService courseInternalService;

    @Override
    public CourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        Course course = new Course();
        course.setTitle(createCourseRequest.getTitle());
        course.setShortDescription(createCourseRequest.getShortDescription());
        course.setDescription(createCourseRequest.getDescription());
        Course savedCourse = courseRepo.save(course);

        return courseInternalService.toCourseResponse(savedCourse);
    }

    @Override
    public CourseResponse getCourse(String id) throws CourseNotFoundException {
        Course project = courseInternalService.findCourseById(id);
        return courseInternalService.toCourseResponse(project);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepo.findAll()
                .stream()
                .map(courseInternalService::toCourseResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseResponse updateCourse(UpdateCourseRequest updateCourseRequest) throws CourseNotFoundException {
        Course course = courseInternalService.findCourseById(updateCourseRequest.getId());
        course.setTitle(updateCourseRequest.getTitle());
        course.setShortDescription(updateCourseRequest.getShortDescription());
        course.setDescription(updateCourseRequest.getDescription());
        course = courseRepo.save(course);

        return courseInternalService.toCourseResponse(course);
    }

    @Override
    public void deleteCourse(String id) throws CourseNotFoundException {
        Course project = courseInternalService.findCourseById(id);
        courseRepo.delete(project);
    }
}

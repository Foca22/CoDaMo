package com.codamo.codamo.service.internal.impl;

import com.codamo.codamo.dto.exceptions.course.CourseNotFoundException;
import com.codamo.codamo.dto.course.response.CourseResponse;
import com.codamo.codamo.dto.exceptions.messages.ExceptionMessages;
import com.codamo.codamo.model.course.Course;
import com.codamo.codamo.repo.CourseRepo;
import com.codamo.codamo.service.internal.CourseInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class CourseInternalServiceImpl implements CourseInternalService {

    private final CourseRepo courseRepo;

    @Autowired
    public CourseInternalServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public Course findCourseById(String id) throws CourseNotFoundException {
        Optional<Course> projectOptional = courseRepo.findById(id);
        if (projectOptional.isEmpty()) {
            throw new CourseNotFoundException(ExceptionMessages.COURSE_NOT_FOUND.getErrorMessage(),
                    ExceptionMessages.COURSE_NOT_FOUND.getHttpStatusCode());
        }
        return projectOptional.get();
    }

    @Override
    public CourseResponse toCourseResponse(Course project) {
        return new CourseResponse(
                project.getId(),
                project.getTitle(),
                project.getShortDescription(),
                project.getDescription(),
                project.getPrice(),
                project.getDiscount(),
                project.getNewPrice(),
                LocalDateTime.ofInstant(project.getDiscountExpiration(), ZoneId.systemDefault()),
                project.getCourseState()
        );
    }
}

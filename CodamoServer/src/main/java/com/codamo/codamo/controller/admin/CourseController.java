package com.codamo.codamo.controller.admin;

import com.codamo.codamo.dto.course.request.CreateCourseRequest;
import com.codamo.codamo.dto.course.request.UpdateCourseRequest;
import com.codamo.codamo.dto.course.response.CourseResponse;
import com.codamo.codamo.dto.exceptions.course.CourseNotFoundException;
import com.codamo.codamo.service.external.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping()
    ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        CourseResponse projectResponse = courseService.createCourse(createCourseRequest);
        return ResponseEntity.ok(projectResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity getCourse(@PathVariable String id) {
        try {
            CourseResponse courseResponse = courseService.getCourse(id);
            return ResponseEntity.ok(courseResponse);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping()
    ResponseEntity getAllCourses() {
        List<CourseResponse> courseResponseList = courseService.getAllCourses();
        return ResponseEntity.ok(courseResponseList);
    }

    @PutMapping()
    ResponseEntity updateCourse(@RequestBody UpdateCourseRequest updateCourseRequest) {
        try {
            CourseResponse courseResponse = courseService.updateCourse(updateCourseRequest);
            return ResponseEntity.ok(courseResponse);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteCourse(@PathVariable String id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

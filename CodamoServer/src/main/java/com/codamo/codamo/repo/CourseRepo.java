package com.codamo.codamo.repo;

import com.codamo.codamo.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, String> {

}

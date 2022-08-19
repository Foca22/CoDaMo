package com.codamo.codamo.repo;

import com.codamo.codamo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, String> {

}

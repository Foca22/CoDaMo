package com.codamo.codamo.repo;

import com.codamo.codamo.model.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lesson, String> {
}

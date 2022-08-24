package com.codamo.codamo.service.internal;

import com.codamo.codamo.dto.exceptions.lesson.LessonNotFoundException;
import com.codamo.codamo.dto.lessons.response.LessonResponse;
import com.codamo.codamo.model.lesson.Lesson;

public interface LessonInternalService {

    Lesson findLessonById(String id) throws LessonNotFoundException;

    LessonResponse toLessonResponse(Lesson lesson);
}

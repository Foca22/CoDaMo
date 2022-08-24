package com.codamo.codamo.service.external;

import com.codamo.codamo.dto.exceptions.lesson.LessonNotFoundException;
import com.codamo.codamo.dto.lessons.request.CreateLessonRequest;
import com.codamo.codamo.dto.lessons.request.UpdateLessonRequest;
import com.codamo.codamo.dto.lessons.response.LessonResponse;

import java.util.List;

public interface LessonService {

    LessonResponse createLesson(CreateLessonRequest createLessonRequest);

    LessonResponse getLesson(String id) throws LessonNotFoundException;

    List<LessonResponse> getAllLessons();

    LessonResponse updateLesson(UpdateLessonRequest updateLessonRequest) throws LessonNotFoundException;

    void deleteLesson(String id) throws LessonNotFoundException;

}

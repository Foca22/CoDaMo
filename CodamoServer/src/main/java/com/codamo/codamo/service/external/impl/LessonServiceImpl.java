package com.codamo.codamo.service.external.impl;

import com.codamo.codamo.dto.exceptions.lesson.LessonNotFoundException;
import com.codamo.codamo.dto.lessons.request.CreateLessonRequest;
import com.codamo.codamo.dto.lessons.request.UpdateLessonRequest;
import com.codamo.codamo.dto.lessons.response.LessonResponse;
import com.codamo.codamo.repo.LessonRepo;
import com.codamo.codamo.service.external.LessonService;
import com.codamo.codamo.service.internal.LessonInternalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepo lessonRepo;

    private final LessonInternalService lessonInternalService;

    public LessonServiceImpl(LessonRepo lessonRepo, LessonInternalService lessonInternalService){
        this.lessonRepo = lessonRepo;
        this.lessonInternalService = lessonInternalService;
    }

    @Override
    public LessonResponse createLesson(CreateLessonRequest createLessonRequest) {
        return null;
    }

    @Override
    public LessonResponse getLesson(String id) throws LessonNotFoundException {
        return null;
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return null;
    }

    @Transactional
    public LessonResponse updateLesson(UpdateLessonRequest updateLessonRequest) throws LessonNotFoundException {
        return null;
    }

    @Override
    public void deleteLesson(String id) throws LessonNotFoundException {

    }
}

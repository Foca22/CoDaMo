package com.codamo.codamo.service.internal.impl;

import com.codamo.codamo.dto.exceptions.lesson.LessonNotFoundException;
import com.codamo.codamo.dto.exceptions.messages.ExceptionMessages;
import com.codamo.codamo.dto.lessons.response.LessonResponse;
import com.codamo.codamo.model.lesson.Lesson;
import com.codamo.codamo.repo.LessonRepo;
import com.codamo.codamo.service.internal.LessonInternalService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonInternalServiceImpl implements LessonInternalService {

    private final LessonRepo lessonRepo;

    public LessonInternalServiceImpl(LessonRepo lessonRepo){
        this.lessonRepo = lessonRepo;
    }

    @Override
    public Lesson findLessonById(String id) throws LessonNotFoundException {
        Optional<Lesson> optionalLesson = lessonRepo.findById(id);
        if (optionalLesson.isEmpty()){
            throw new LessonNotFoundException(ExceptionMessages.LESSON_NOT_FOUND.getErrorMessage(),
                    ExceptionMessages.LESSON_NOT_FOUND.getHttpStatusCode());
        }
        return optionalLesson.get();
    }

    @Override
    public LessonResponse toLessonResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getType(),
                lesson.getComments(),
                lesson.getChapter().getId());
    }
}

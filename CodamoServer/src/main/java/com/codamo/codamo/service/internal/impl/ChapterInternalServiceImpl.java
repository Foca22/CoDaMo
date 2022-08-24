package com.codamo.codamo.service.internal.impl;

import com.codamo.codamo.dto.chapter.response.ChapterResponse;
import com.codamo.codamo.dto.exceptions.chapter.ChapterNotFoundException;
import com.codamo.codamo.dto.exceptions.messages.ExceptionMessages;
import com.codamo.codamo.model.chapter.Chapter;
import com.codamo.codamo.model.course.Course;
import com.codamo.codamo.repo.ChapterRepo;
import com.codamo.codamo.service.internal.ChapterInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterInternalServiceImpl implements ChapterInternalService {

    private final ChapterRepo chapterRepo;

    @Autowired
    public ChapterInternalServiceImpl(ChapterRepo chapterRepo){
        this.chapterRepo = chapterRepo;
    }

    @Override
    public Chapter findChapterById(String id) throws ChapterNotFoundException {
        Optional<Chapter> chapterOptional = chapterRepo.findById(id);
        if(chapterOptional.isEmpty()){
            throw new ChapterNotFoundException(ExceptionMessages.CHAPTER_NOT_FOUND.getErrorMessage(),
                    ExceptionMessages.CHAPTER_NOT_FOUND.getHttpStatusCode());
        }
        return chapterOptional.get();
    }

    @Override
    public ChapterResponse toChapterResponse(Chapter chapter) {
        return new ChapterResponse(
                chapter.getId(),
                chapter.getTitle(),
                chapter.getDescription(),
                chapter.getShortDescription(),
                chapter.getCourse().getId()
        );
    }
}

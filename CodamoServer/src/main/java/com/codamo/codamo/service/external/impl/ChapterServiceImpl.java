package com.codamo.codamo.service.external.impl;

import com.codamo.codamo.dto.chapter.request.CreateChapterRequest;
import com.codamo.codamo.dto.chapter.request.UpdateChapterRequest;
import com.codamo.codamo.dto.chapter.response.ChapterResponse;
import com.codamo.codamo.dto.exceptions.chapter.ChapterNotFoundException;
import com.codamo.codamo.model.chapter.Chapter;
import com.codamo.codamo.repo.ChapterRepo;
import com.codamo.codamo.service.external.ChapterService;
import com.codamo.codamo.service.internal.ChapterInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepo chapterRepo;

    private final ChapterInternalService chapterInternalService;

    @Autowired
    public ChapterServiceImpl(ChapterRepo chapterRepo, ChapterInternalService chapterInternalService) {
        this.chapterRepo = chapterRepo;
        this.chapterInternalService = chapterInternalService;
    }


    @Override
    public ChapterResponse createChapter(CreateChapterRequest createChapterRequest) {
        Chapter chapter = new Chapter();
        chapter.setTitle(createChapterRequest.getTitle());
        chapter.setShortDescription(createChapterRequest.getShortDescription());
        chapter.setDescription(createChapterRequest.getDescription());
        Chapter savedChapter = chapterRepo.save(chapter);

        return chapterInternalService.toChapterResponse(savedChapter);
    }

    @Override
    public ChapterResponse getChapter(String id) throws ChapterNotFoundException {
        Chapter chapter = chapterInternalService.findChapterById(id);
        return chapterInternalService.toChapterResponse(chapter);
    }

    @Override
    public List<ChapterResponse> getAllChapters() {
        return chapterRepo.findAll().stream()
                .map(chapter -> chapterInternalService.toChapterResponse(chapter))
                .collect(Collectors.toList());
    }

    @Transactional
    public ChapterResponse updateChapter(UpdateChapterRequest updateChapterRequest) throws ChapterNotFoundException {
        Chapter chapter = chapterInternalService.findChapterById(updateChapterRequest.getId());
        chapter.setTitle(updateChapterRequest.getTitle());
        chapter.setShortDescription(updateChapterRequest.getShortDescription());
        chapter.setDescription(updateChapterRequest.getDescription());
        chapter = chapterRepo.save(chapter);

        return chapterInternalService.toChapterResponse(chapter);
    }

    @Override
    public void deleteChapter(String id) throws ChapterNotFoundException {
        Chapter chapter = chapterInternalService.findChapterById(id);
        chapterRepo.delete(chapter);
    }
}

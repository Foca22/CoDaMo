package com.codamo.codamo.service.external;

import com.codamo.codamo.dto.chapter.request.CreateChapterRequest;
import com.codamo.codamo.dto.chapter.request.UpdateChapterRequest;
import com.codamo.codamo.dto.chapter.response.ChapterResponse;
import com.codamo.codamo.dto.exceptions.chapter.ChapterNotFoundException;
import com.codamo.codamo.model.course.Course;

import java.util.List;

public interface ChapterService {

    ChapterResponse createChapter(CreateChapterRequest createChapterRequest, Course course);

    ChapterResponse getChapter(String id) throws ChapterNotFoundException;

    List<ChapterResponse> getAllChapters();

    ChapterResponse updateChapter(UpdateChapterRequest updateChapterRequest) throws ChapterNotFoundException;

    void deleteChapter(String id) throws ChapterNotFoundException;
}

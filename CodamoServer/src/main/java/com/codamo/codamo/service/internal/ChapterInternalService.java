package com.codamo.codamo.service.internal;

import com.codamo.codamo.dto.chapter.response.ChapterResponse;
import com.codamo.codamo.dto.exceptions.chapter.ChapterNotFoundException;
import com.codamo.codamo.model.chapter.Chapter;

public interface ChapterInternalService {

    Chapter findChapterById(String id) throws ChapterNotFoundException;

    ChapterResponse toChapterResponse(Chapter chapter);
}

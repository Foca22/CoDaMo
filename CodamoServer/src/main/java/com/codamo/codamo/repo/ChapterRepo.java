package com.codamo.codamo.repo;

import com.codamo.codamo.model.chapter.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepo extends JpaRepository<Chapter, String> {
}

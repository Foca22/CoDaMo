package com.codamo.codamo.controller.admin;

import com.codamo.codamo.dto.chapter.request.CreateChapterRequest;
import com.codamo.codamo.dto.chapter.request.UpdateChapterRequest;
import com.codamo.codamo.dto.chapter.response.ChapterResponse;
import com.codamo.codamo.dto.exceptions.chapter.ChapterNotFoundException;
import com.codamo.codamo.service.external.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService){
        this.chapterService = chapterService;
    }

    @PostMapping()
    ResponseEntity<?> createChapter(@RequestBody CreateChapterRequest createChapterRequest){
        ChapterResponse chapterResponse = chapterService.createChapter(createChapterRequest);
        return ResponseEntity.ok(chapterResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity getChapter(@PathVariable String id) {
        try {
            ChapterResponse chapterResponse = chapterService.getChapter(id);
            return ResponseEntity.ok(chapterResponse);
        } catch (ChapterNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping()
    ResponseEntity getAllChapters() {
        List<ChapterResponse> chapterResponseList = chapterService.getAllChapters();
        return ResponseEntity.ok(chapterResponseList);
    }

    @PutMapping()
    ResponseEntity updateChapter(UpdateChapterRequest updateChapterRequest) {
        try {
            ChapterResponse chapterResponse = chapterService.updateChapter(updateChapterRequest);
            return ResponseEntity.ok(chapterResponse);
        } catch (ChapterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteChapter(@PathVariable String id){
        try {
            chapterService.deleteChapter(id);
            return ResponseEntity.ok().build();
        } catch (ChapterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

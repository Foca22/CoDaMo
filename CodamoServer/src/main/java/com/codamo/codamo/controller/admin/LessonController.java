package com.codamo.codamo.controller.admin;

import com.codamo.codamo.dto.lessons.request.CreateLessonRequest;
import com.codamo.codamo.dto.lessons.request.UpdateLessonRequest;
import com.codamo.codamo.service.external.LessonService;
import com.codamo.codamo.service.internal.ChapterInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/lesson")
public class LessonController {

    private final LessonService lessonService;

    private final ChapterInternalService chapterInternalService;

    @Autowired
    public LessonController(LessonService lessonService, ChapterInternalService chapterInternalService) {
        this.lessonService = lessonService;
        this.chapterInternalService = chapterInternalService;
    }

    @PostMapping()
    ResponseEntity<?> createLesson(@RequestBody @Valid CreateLessonRequest createLessonRequest){

    }

    @GetMapping("/{id}")
    ResponseEntity getLesson(@PathVariable String id){

    }

    @GetMapping()
    ResponseEntity getAllLessons(){

    }

    @PutMapping()
    ResponseEntity updateLesson(@RequestBody @Valid UpdateLessonRequest updateLessonRequest){

    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteLesson(@PathVariable String id){

    }
}

package com.codamo.codamo.controller.admin;

import com.codamo.codamo.dto.comment.request.CreateCommentRequest;
import com.codamo.codamo.dto.comment.request.UpdateCommentRequest;
import com.codamo.codamo.service.external.CommentService;
import com.codamo.codamo.service.internal.AccountInternalService;
import com.codamo.codamo.service.internal.LessonInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin/comment")
public class CommentController {

    private final CommentService commentService;

    private final AccountInternalService accountInternalService;

    private final LessonInternalService lessonInternalService;

    @Autowired
    public CommentController(CommentService commentService, AccountInternalService accountInternalService,
                             LessonInternalService lessonInternalService) {
        this.commentService = commentService;
        this.accountInternalService = accountInternalService;
        this.lessonInternalService = lessonInternalService;
    }

    @PostMapping()
    ResponseEntity<?> createComment(@RequestBody @Valid CreateCommentRequest){

    }

    @GetMapping("/{id}")
    ResponseEntity getComment(@PathVariable String id){

    }

    @GetMapping()
    ResponseEntity getAllComments() {

    }

    @PutMapping()
    ResponseEntity updateComment(@RequestBody UpdateCommentRequest updateCommentRequest){

    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteComment(@PathVariable String id){

    }




}

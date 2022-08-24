package com.codamo.codamo.dto.lessons.response;

import com.codamo.codamo.model.comment.Comment;
import com.codamo.codamo.model.lesson.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {

    private String id;

    private String title;

    private LessonType type;

    private Set<Comment> comments;

    private String chapter;
}

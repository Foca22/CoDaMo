package com.codamo.codamo.dto.lessons.request;

import com.codamo.codamo.model.lesson.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLessonRequest {

    @NotNull(message = "lesson.id.cannot.be.null")
    private String title;

    @NotNull(message = "lesson.type.cannot.be.null")
    private LessonType type;

    @NotNull(message = "lesson.chapterId.cannot.be.null")
    private String chapterId;
}

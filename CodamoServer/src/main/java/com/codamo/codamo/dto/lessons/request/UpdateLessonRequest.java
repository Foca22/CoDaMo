package com.codamo.codamo.dto.lessons.request;

import com.codamo.codamo.model.lesson.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLessonRequest {

    @NotNull(message = "lesson.id.cannot.be.null")
    private String id;

    private String title;

    private LessonType type;

    private String chapterId;
}

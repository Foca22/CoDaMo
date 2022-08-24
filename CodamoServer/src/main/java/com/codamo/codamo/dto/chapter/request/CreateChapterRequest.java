package com.codamo.codamo.dto.chapter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChapterRequest {

    @NotNull(message = "chapter.title.cannot.be.null")
    private String title;

    @NotNull(message = "chapter.shortDescription.cannot.be.null")
    private String shortDescription;

    @NotNull(message = "chapter.description.cannot.be.null")
    private String description;

    @NotNull(message = "chapter.courseId.cannot.be.null")
    private String courseId;
}

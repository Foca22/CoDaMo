package com.codamo.codamo.dto.chapter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChapterRequest {

    @NotNull(message = "chapter.id.cannot.be.null")
    private String id;

    private String title;

    private String description;

    private String shortDescription;

    private String courseId;
}

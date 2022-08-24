package com.codamo.codamo.dto.chapter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChapterRequest {

    private String id;

    private String title;

    private String description;

    private String shortDescription;
}

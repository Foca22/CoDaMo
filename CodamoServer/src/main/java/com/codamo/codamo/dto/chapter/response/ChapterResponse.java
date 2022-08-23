package com.codamo.codamo.dto.chapter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterResponse {

    private String id;

    private String title;

    private String description;

    private String shortDescription;
}

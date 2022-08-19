package com.codamo.codamo.dto.course.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseRequest {
    private String id;
    private String title;
    private String description;
}

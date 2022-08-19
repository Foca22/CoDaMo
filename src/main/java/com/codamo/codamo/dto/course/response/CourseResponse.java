package com.codamo.codamo.dto.course.response;

import com.codamo.codamo.model.CourseState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private String id;
    private String title;
    private String description;
    private float price;
    private float discount;
    private float newPrice;
    private Instant discountExpiration;
    private CourseState courseState;
}

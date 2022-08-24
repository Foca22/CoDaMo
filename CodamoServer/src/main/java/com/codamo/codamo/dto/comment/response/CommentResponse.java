package com.codamo.codamo.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private String id;

    private String text;

    private Instant createdAt;

    private String accountId;

    private String lessonId;
}

package com.codamo.codamo.dto.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentRequest {

    @NotNull(message = "comment.id.cannot.be.null")
    private String id;

    private String text;

    private Instant createdAt;

    private String accountId;

    private String lessonId;
}

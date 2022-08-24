package com.codamo.codamo.dto.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotNull(message = "comment.text.cannot.be.null")
    private String text;

    @NotNull(message = "comment.createdAt.cannot.be.null")
    private Instant createdAt;

    @NotNull(message = "comment.accountId.cannot.be.null")
    private String accountId;

    @NotNull(message = "comment.lessonId.cannot.be.null")
    private String lessonId;
}

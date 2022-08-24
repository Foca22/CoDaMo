package com.codamo.codamo.repo;

import com.codamo.codamo.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, String> {
}

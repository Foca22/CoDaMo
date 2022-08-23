package com.codamo.codamo.model.lesson;

import com.codamo.codamo.model.chapter.Chapter;
import com.codamo.codamo.model.comment.Comment;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "lesson_id", updatable = false, nullable = false)
    private String id;

    @Lob
    private String title;

    private LessonType type;

    @OneToMany(mappedBy = "lesson")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    public Lesson() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}

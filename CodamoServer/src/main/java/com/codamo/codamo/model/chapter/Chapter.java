package com.codamo.codamo.model.chapter;

import com.codamo.codamo.model.course.Course;
import com.codamo.codamo.model.lesson.Lesson;
import com.codamo.codamo.model.status.UserChapterStatus;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "chapter_id", updatable = false, nullable = false)
    private String id;

    private String title;

    @Lob
    private String description;

    private String shortDescription;

    @OneToMany(mappedBy = "chapter")
    private Set<Lesson> lessons = new HashSet<>();

    @OneToMany(mappedBy = "chapter")
    private Set<UserChapterStatus> userChapterStatuses = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Chapter() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<UserChapterStatus> getUserChapterStatuses() {
        return userChapterStatuses;
    }

    public void setUserChapterStatuses(Set<UserChapterStatus> userChapterStatuses) {
        this.userChapterStatuses = userChapterStatuses;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

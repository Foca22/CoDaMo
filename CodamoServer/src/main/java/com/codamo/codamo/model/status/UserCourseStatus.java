package com.codamo.codamo.model.status;

import com.codamo.codamo.model.course.Course;
import com.codamo.codamo.model.auth.Account;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
public class UserCourseStatus {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_course_status_id", updatable = false, nullable = false)
    private String id;

    private Status status;

    private Instant enrolledAt;

    private Instant finishedAt;

    private Float progress;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public UserCourseStatus() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Instant enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

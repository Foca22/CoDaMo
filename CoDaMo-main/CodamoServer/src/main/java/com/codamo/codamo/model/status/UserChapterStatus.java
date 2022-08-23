package com.codamo.codamo.model.status;

import com.codamo.codamo.model.chapter.Chapter;
import com.codamo.codamo.model.auth.Account;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
public class UserChapterStatus {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_chapter_status_id", updatable = false, nullable = false)
    private String id;

    private Status status;

    private Instant startedAt;

    private Instant finishedAt;

    private Float progress;

    @ManyToOne()
    @JoinColumn(name = "account_id", updatable = false, nullable = false)
    private Account user;

    @ManyToOne
    @JoinColumn(name = "chapter_id", updatable = false, nullable = false)
    private Chapter chapter;


    public UserChapterStatus() {

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

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}

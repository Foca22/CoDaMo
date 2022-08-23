package com.codamo.codamo.model.auth;

import com.codamo.codamo.model.status.UserChapterStatus;
import com.codamo.codamo.model.status.UserCourseStatus;
import com.codamo.codamo.model.status.UserLessonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "account_id", updatable = false, nullable = false)
    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String fullName;

    @OneToOne
    private RefreshToken refreshToken;

    @OneToOne
    private EmailVerification emailVerification;

    private String fcmToken;

    private boolean verified;

    @OneToMany()
    private Set<UserCourseStatus> courseStatuses = new HashSet<>();

    @OneToMany()
    private Set<UserChapterStatus> chapterStatuses = new HashSet<>();

    @OneToMany()
    private Set<UserLessonStatus> lessonStatuses = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="Authorities", joinColumns=@JoinColumn(name="user_id"))
    private List<String> authorities;

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public EmailVerification getEmailVerification() {
        return emailVerification;
    }

    public void setEmailVerification(EmailVerification emailVerification) {
        this.emailVerification = emailVerification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public Set<UserCourseStatus> getCourseStatuses() {
        return courseStatuses;
    }

    public void setCourseStatuses(Set<UserCourseStatus> courseStatuses) {
        this.courseStatuses = courseStatuses;
    }

    public Set<UserChapterStatus> getChapterStatuses() {
        return chapterStatuses;
    }

    public void setChapterStatuses(Set<UserChapterStatus> chapterStatuses) {
        this.chapterStatuses = chapterStatuses;
    }

    public Set<UserLessonStatus> getLessonStatuses() {
        return lessonStatuses;
    }

    public void setLessonStatuses(Set<UserLessonStatus> lessonStatuses) {
        this.lessonStatuses = lessonStatuses;
    }
}

package com.codamo.codamo.model.course;

import com.codamo.codamo.model.chapter.Chapter;
import com.codamo.codamo.model.status.UserCourseStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "project_id", updatable = false, nullable = false)
    private String id;

    private String title;

    private String shortDescription;

    @Lob
    private String description;

    private float price;

    private float discount;

    private float newPrice;

    private Instant discountExpiration;

    @Enumerated(EnumType.STRING)
    private CourseState courseState;

    @OneToMany(mappedBy = "course")
    private Set<Chapter> chapters = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<UserCourseStatus> userCourseStatuses = new HashSet<>();

    public Course() {
        price = 0;
        discount = 0;
        newPrice = 0;
        courseState = CourseState.DRAFT;
        discountExpiration = Instant.now();
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    public Instant getDiscountExpiration() {
        return discountExpiration;
    }

    public void setDiscountExpiration(Instant discountExpiration) {
        this.discountExpiration = discountExpiration;
    }

    public CourseState getCourseState() {
        return courseState;
    }

    public void setCourseState(CourseState courseState) {
        this.courseState = courseState;
    }
}
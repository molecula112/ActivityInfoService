package com.ybondarenko.ActivityInfo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "mentor_name")
    private String mentorName;
    @Column(name = "activity_name")
    private String activityName;
    @Column(name = "date")
    private String date;
    @Column(name = "total_point_count")
    private int totalPointCount;
    @Column(name = "user_id")
    private Long userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private List<QuestionInfo> questionInfos;

    public Activity(String mentorName, String activityName, Long userId) {
        this.mentorName = mentorName;
        this.activityName = activityName;
        this.userId = userId;
        questionInfos = new ArrayList<>();
    }

    public Activity() {
        questionInfos = new ArrayList<>();
    }
}

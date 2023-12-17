package com.ybondarenko.ActivityInfo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("Activity")
public class Activity {
    @Id
    private String id;
    private String mentorName;
    private String activityName;
    private String date;
    private int totalPointCount;
    private String userId;
    private List<QuestionInfo> questionInfos;

    public Activity(String mentorName, String activityName, String userId) {
        this.mentorName = mentorName;
        this.activityName = activityName;
        this.userId = userId;
        questionInfos = new ArrayList<>();
    }

    public Activity(String mentorName, String activityName) {
        this.mentorName = mentorName;
        this.activityName = activityName;
        questionInfos = new ArrayList<>();
    }

    public Activity() {
        questionInfos = new ArrayList<>();
    }
}

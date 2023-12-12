package com.ybondarenko.ActivityInfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private String fromDate;
    private String toDate;
    private int totalPointCount;
    private String userId;
    private List<QuestionInfo> questionInfos;

    public Activity(String mentorName, String activityName, String fromDate, String toDate, String userId) {
        this.mentorName = mentorName;
        this.activityName = activityName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.userId = userId;
        questionInfos = new ArrayList<>();
    }

    public Activity() {
        questionInfos = new ArrayList<>();
    }
}

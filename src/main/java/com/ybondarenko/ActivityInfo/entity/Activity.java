package com.ybondarenko.ActivityInfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Activity")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activity {
    @Id
    private String id;
    private String mentorName;
    private String activityName;
    private String fromDate;
    private String toDate;
    private int totalPointCount;
    private String userId;
    @DBRef
    private List<QuestionInfo> questionInfos;
}

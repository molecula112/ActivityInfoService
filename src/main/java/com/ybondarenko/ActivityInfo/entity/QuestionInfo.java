package com.ybondarenko.ActivityInfo.entity;

import lombok.Data;

@Data
public class QuestionInfo {
    private String activityId;
    private String question;
    private int points;
}

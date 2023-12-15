package com.ybondarenko.ActivityInfo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question_info")
public class QuestionInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "activity_id")
    private Long activityId;
    @Column(name = "question")
    private String question;
    @Column(name = "points")
    private int points;
}

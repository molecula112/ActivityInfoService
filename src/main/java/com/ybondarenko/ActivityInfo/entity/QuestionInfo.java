package com.ybondarenko.ActivityInfo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("QuestionInfo")
public class QuestionInfo {
    @Id
    private String id;
    private String question;
    private int points;
}

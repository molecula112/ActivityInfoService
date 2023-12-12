package com.ybondarenko.ActivityInfo.repository;

import com.ybondarenko.ActivityInfo.entity.QuestionInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionInfoRepository extends MongoRepository<QuestionInfo, String> {
}

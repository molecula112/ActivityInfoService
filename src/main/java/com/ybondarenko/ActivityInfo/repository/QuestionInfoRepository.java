package com.ybondarenko.ActivityInfo.repository;

import com.ybondarenko.ActivityInfo.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionInfoRepository extends MongoRepository<Activity, String> {
}

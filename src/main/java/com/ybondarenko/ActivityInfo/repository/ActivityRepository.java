package com.ybondarenko.ActivityInfo.repository;

import com.ybondarenko.ActivityInfo.entity.Activity;
import com.ybondarenko.ActivityInfo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByUserId(String userId);
}

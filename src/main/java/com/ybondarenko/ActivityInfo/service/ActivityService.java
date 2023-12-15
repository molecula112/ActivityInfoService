package com.ybondarenko.ActivityInfo.service;

import com.ybondarenko.ActivityInfo.entity.Activity;
import com.ybondarenko.ActivityInfo.entity.User;
import com.ybondarenko.ActivityInfo.exceptions.ActivityNotFoundException;
import com.ybondarenko.ActivityInfo.exceptions.UserNotFoundException;
import com.ybondarenko.ActivityInfo.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public List<Activity> findByUserId(Long userId) {
        return activityRepository.findByUserId(userId);
    }

    public Activity findById(Long ActivityId) throws ActivityNotFoundException {
        return activityRepository.findById(ActivityId).orElseThrow(() -> new ActivityNotFoundException("User not found"));
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }


}

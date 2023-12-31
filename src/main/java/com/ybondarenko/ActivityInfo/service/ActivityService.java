package com.ybondarenko.ActivityInfo.service;

import com.ybondarenko.ActivityInfo.entity.Activity;
import com.ybondarenko.ActivityInfo.exceptions.ActivityNotFoundException;
import com.ybondarenko.ActivityInfo.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public List<Activity> findByUserId(String userId) {
        return activityRepository.findByUserId(userId);
    }

    public Activity findById(String ActivityId) throws ActivityNotFoundException {
        return activityRepository.findById(ActivityId).orElseThrow(() -> new ActivityNotFoundException("Activity not found"));
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }


}

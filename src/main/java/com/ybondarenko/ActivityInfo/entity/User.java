package com.ybondarenko.ActivityInfo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("User")
@Data
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private List<Activity> activities;

    public User() {
        activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setUserId(this.id);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }
}

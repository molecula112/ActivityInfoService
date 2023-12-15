package com.ybondarenko.ActivityInfo.controller;

import com.ybondarenko.ActivityInfo.entity.Activity;
import com.ybondarenko.ActivityInfo.entity.QuestionInfo;
import com.ybondarenko.ActivityInfo.entity.User;
import com.ybondarenko.ActivityInfo.exceptions.ActivityNotFoundException;
import com.ybondarenko.ActivityInfo.exceptions.UserNotFoundException;
import com.ybondarenko.ActivityInfo.service.ActivityService;
import com.ybondarenko.ActivityInfo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ActivityService activityService;

    @GetMapping("/{userId}")
    public String showUserDetails(@PathVariable Long userId, Model model) throws UserNotFoundException {
        User user = userService.findById(userId);
        List<Activity> activities = activityService.findByUserId(userId);
        user.setActivities(activities);
        model.addAttribute("user", user);
        model.addAttribute("activity", new Activity());
        return "user-details";
    }

    @GetMapping("/{userId}/activities/{activityId}/question-info")
    public String showQuestionInfoForm(@PathVariable Long activityId, Model model) throws ActivityNotFoundException {
        Activity activity = activityService.findById(activityId);
        model.addAttribute("activity", activity);
        model.addAttribute("questionInfo", new QuestionInfo());
        return "question-info-form";
    }

    @PostMapping("/search")
    public String searchUser(@ModelAttribute User user, Model model) {
        List<User> users = userService.findByName(user.getName());
        if (users.isEmpty()) {
            return "redirect:/activities/new";
        } else {
            model.addAttribute("users", users);
            return "search";
        }
    }

    @PostMapping("/{userId}/activities")
    public String addActivityToUser(@PathVariable Long userId,
                                    @ModelAttribute Activity activity) throws UserNotFoundException {
        User user = userService.findById(userId);
        activity.setUserId(userId);
        user.addActivity(activity);
        activityService.save(activity);
        userService.save(user);
        return "redirect:/users/" + userId;
    }

    @PostMapping("/{userId}/activities/{activityId}/question-info")
    public String addQuestionInfoToActivity(@PathVariable Long userId,
                                            @PathVariable Long activityId,
                                            @ModelAttribute QuestionInfo questionInfo) throws ActivityNotFoundException {
        Activity activity = activityService.findById(activityId);
        activity.getQuestionInfos().add(questionInfo);
        questionInfo.setActivityId(activityId);
        activity.setTotalPointCount(activity.getTotalPointCount() + questionInfo.getPoints());
        activityService.save(activity);
        return "redirect:/users/" + userId;
    }
}
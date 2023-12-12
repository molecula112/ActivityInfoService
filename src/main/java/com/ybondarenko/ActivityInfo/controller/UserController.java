package com.ybondarenko.ActivityInfo.controller;

import com.ybondarenko.ActivityInfo.entity.Activity;
import com.ybondarenko.ActivityInfo.entity.QuestionInfo;
import com.ybondarenko.ActivityInfo.entity.User;
import com.ybondarenko.ActivityInfo.repository.ActivityRepository;
import com.ybondarenko.ActivityInfo.repository.QuestionInfoRepository;
import com.ybondarenko.ActivityInfo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final QuestionInfoRepository questionInfoRepository;

    public UserController(UserRepository userRepository, ActivityRepository activityRepository, QuestionInfoRepository questionInfoRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.questionInfoRepository = questionInfoRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("/users/search")
    public String searchUser(@ModelAttribute User user, Model model) {
        List<User> users = userRepository.findByName(user.getName());
        if (users.isEmpty()) {
            return "redirect:/activities/new";
        } else {
            model.addAttribute("users", users);
            return "search";
        }
    }

    @GetMapping("/users/{userId}")
    public String showUserDetails(@PathVariable String userId, Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Activity> activities = activityRepository.findByUserId(userId);
        user.setActivities(activities);
        model.addAttribute("user", user);
        model.addAttribute("activity", new Activity());
        return "user-details";
    }

    @PostMapping("/users/{userId}/activities")
    public String addActivityToUser(@PathVariable String userId, @ModelAttribute Activity activity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        activity.setUserId(userId);
        user.addActivity(activity);
        activityRepository.save(activity);
        userRepository.save(user);
        return "redirect:/users/" + userId;
    }

    @GetMapping("/users/{userId}/activities/{activityId}/question-info")
    public String showQuestionInfoForm(@PathVariable String userId, @PathVariable String activityId, Model model) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found"));
        model.addAttribute("activity", activity);
        model.addAttribute("questionInfo", new QuestionInfo());
        return "question-info-form";
    }

    @PostMapping("/users/{userId}/activities/{activityId}/question-info")
    public String addQuestionInfoToActivity(@PathVariable String userId, @PathVariable String activityId, @ModelAttribute QuestionInfo questionInfo) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found"));
        activity.getQuestionInfos().add(questionInfo);
        questionInfo.setActivityId(activityId);
        activity.setTotalPointCount(activity.getTotalPointCount() + questionInfo.getPoints());
        activityRepository.save(activity);
        //questionInfoRepository.save(questionInfo);
        //return "redirect:/users/" + userId + "/activities/" + activityId;
        return "redirect:/users/" + userId;
    }

    @GetMapping("/activities/new")
    public String showNewActivityForm(Model model) {
        model.addAttribute("activity", new Activity());
        return "new-activity-form";
    }

    @PostMapping("/activities/new")
    public String createNewActivity(@RequestParam("name") String name,
                                    @RequestParam("activityName") String activityName,
                                    @RequestParam("mentorName") String mentorName,
                                    @RequestParam("fromDate") String fromDate,
                                    @RequestParam("toDate") String toDate) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        Activity activity = new Activity();
        activity.setMentorName(mentorName);
        activity.setActivityName(activityName);
        activity.setFromDate(fromDate);
        activity.setToDate(toDate);
        activity.setUserId(user.getId());
        activityRepository.save(activity);
        userRepository.save(user);

        return "redirect:/users/" + user.getId();
    }

    @GetMapping("/question-info-activity/{activityId}")
    public String showQuestionInfoActivity(@PathVariable String activityId, Model model) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found"));
        model.addAttribute("activity", activity);
        return "question-info-activity";
    }
}
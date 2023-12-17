package com.ybondarenko.ActivityInfo.controller;

import com.ybondarenko.ActivityInfo.entity.Activity;
import com.ybondarenko.ActivityInfo.entity.User;
import com.ybondarenko.ActivityInfo.exceptions.ActivityNotFoundException;
import com.ybondarenko.ActivityInfo.service.ActivityService;
import com.ybondarenko.ActivityInfo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/new")
    public String showNewActivityForm(Model model) {
        model.addAttribute("activity", new Activity());
        return "new-user-form";
    }

    @GetMapping("/{activityId}/question-info-activity")
    public String showQuestionInfoActivity(@PathVariable String activityId, Model model) throws ActivityNotFoundException {
        Activity activity = activityService.findById(activityId);
        model.addAttribute("activity", activity);
        return "question-info-activity";
    }
}

package com.cfs.ResumeAnalyser.controller;

import com.cfs.ResumeAnalyser.dto.DashboardResponse;
import com.cfs.ResumeAnalyser.service.DashboardService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final DashboardService dashboardService;

    public UserController(
            DashboardService dashboardService) {

        this.dashboardService =
                dashboardService;
    }

    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return dashboardService
                .getDashboard(email);
    }
}
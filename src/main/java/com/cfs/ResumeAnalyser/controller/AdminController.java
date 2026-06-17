package com.cfs.ResumeAnalyser.controller;

import com.cfs.ResumeAnalyser.dto.DashboardStats;
import com.cfs.ResumeAnalyser.model.ResumeAnalysis;
import com.cfs.ResumeAnalyser.repository.AtsReportRepository;
import com.cfs.ResumeAnalyser.repository.ResumeAnalysisRepository;
import com.cfs.ResumeAnalyser.repository.UserRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ResumeAnalysisRepository resumeRepository;
    private final AtsReportRepository atsRepository;
    private final UserRepository userRepository;

    public AdminController(
            ResumeAnalysisRepository resumeRepository,
            AtsReportRepository atsRepository,
            UserRepository userRepository) {

        this.resumeRepository = resumeRepository;
        this.atsRepository = atsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all-resumes")
    public List<ResumeAnalysis> getAllResumes() {

        return resumeRepository.findAll();
    }

    @GetMapping("/dashboard")
    public DashboardStats getDashboardStats() {

        return new DashboardStats(
                userRepository.count(),
                resumeRepository.count(),
                atsRepository.count()
        );
    }
}
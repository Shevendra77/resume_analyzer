package com.cfs.ResumeAnalyser.controller;

import com.cfs.ResumeAnalyser.dto.DashboardResponse;
import com.cfs.ResumeAnalyser.model.AtsReport;
import com.cfs.ResumeAnalyser.model.User;
import com.cfs.ResumeAnalyser.repository.AtsReportRepository;
import com.cfs.ResumeAnalyser.repository.ResumeAnalysisRepository;
import com.cfs.ResumeAnalyser.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final ResumeAnalysisRepository resumeRepository;
    private final AtsReportRepository atsRepository;

    public UserController(
            UserRepository userRepository,
            ResumeAnalysisRepository resumeRepository,
            AtsReportRepository atsRepository) {

        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.atsRepository = atsRepository;
    }

    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        long totalResume =
                resumeRepository.countByUser(user);

        long totalAts =
                atsRepository.countByUser(user);

        int latestScore = 0;

        try {

            List<AtsReport> reports =
                    atsRepository.findByUserOrderByCreatedAtDesc(user);

            System.out.println("REPORT COUNT = " + reports.size());

            if (!reports.isEmpty()) {

                AtsReport latestReport =
                        reports.get(0);

                String reportJson =
                        latestReport.getReport();

                System.out.println(
                        "LATEST ATS REPORT = "
                                + reportJson
                );

                // Remove markdown if present
                reportJson = reportJson
                        .replace("```json", "")
                        .replace("```", "")
                        .trim();

                ObjectMapper mapper =
                        new ObjectMapper();

                JsonNode node =
                        mapper.readTree(reportJson);

                if (node.has("atsScore")) {

                    latestScore =
                            node.get("atsScore")
                                    .asInt();

                    System.out.println(
                            "LATEST SCORE = "
                                    + latestScore
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR READING ATS SCORE"
            );

            e.printStackTrace();
        }

        return new DashboardResponse(
                totalResume,
                totalAts,
                latestScore,
                "Active"
        );
    }
}
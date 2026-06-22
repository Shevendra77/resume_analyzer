package com.cfs.ResumeAnalyser.service;

import com.cfs.ResumeAnalyser.dto.DashboardResponse;
import com.cfs.ResumeAnalyser.model.AtsReport;
import com.cfs.ResumeAnalyser.model.User;
import com.cfs.ResumeAnalyser.repository.AtsReportRepository;
import com.cfs.ResumeAnalyser.repository.ResumeAnalysisRepository;
import com.cfs.ResumeAnalyser.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ResumeAnalysisRepository resumeRepository;
    private final AtsReportRepository atsRepository;

    public DashboardService(
            UserRepository userRepository,
            ResumeAnalysisRepository resumeRepository,
            AtsReportRepository atsRepository) {

        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.atsRepository = atsRepository;
    }

    @Cacheable(value = "dashboard", key = "#email")
    public DashboardResponse getDashboard(String email) {

        System.out.println("FETCHING DASHBOARD FROM DATABASE...");

        User user = userRepository.findByEmail(email).orElseThrow();

        long totalResume = resumeRepository.countByUser(user);
        long totalAts = atsRepository.countByUser(user);

        int latestScore = 0;

        try {

            List<AtsReport> reports =
                    atsRepository.findByUserOrderByCreatedAtDesc(user);

            if (!reports.isEmpty()) {

                AtsReport latestReport = reports.get(0);
                String reportJson = latestReport.getReport();

                reportJson = reportJson
                        .replace("```json", "")
                        .replace("```", "")
                        .trim();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(reportJson);

                if (node.has("atsScore")) {
                    latestScore = node.get("atsScore").asInt();
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR READING ATS SCORE");
            e.printStackTrace();
        }

        return new DashboardResponse(totalResume, totalAts, latestScore, "Active");
    }

    @CacheEvict(value = "dashboard", key = "#email")
    public void evictDashboardCache(String email) {
        System.out.println("CACHE EVICTED FOR: " + email);
    }
}
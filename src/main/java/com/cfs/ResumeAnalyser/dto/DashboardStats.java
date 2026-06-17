package com.cfs.ResumeAnalyser.dto;

public class DashboardStats {

    private long totalUsers;
    private long totalResumeAnalyses;
    private long totalAtsReports;

    public DashboardStats(
            long totalUsers,
            long totalResumeAnalyses,
            long totalAtsReports) {

        this.totalUsers = totalUsers;
        this.totalResumeAnalyses = totalResumeAnalyses;
        this.totalAtsReports = totalAtsReports;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalResumeAnalyses() {
        return totalResumeAnalyses;
    }

    public long getTotalAtsReports() {
        return totalAtsReports;
    }
}
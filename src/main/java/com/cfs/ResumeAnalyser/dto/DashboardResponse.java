
package com.cfs.ResumeAnalyser.dto;

public class DashboardResponse {

    private long totalResume;
    private long totalAts;
    private int latestScore;
    private String status;

    public DashboardResponse(
            long totalResume,
            long totalAts,
            int latestScore,
            String status) {

        this.totalResume = totalResume;
        this.totalAts = totalAts;
        this.latestScore = latestScore;
        this.status = status;
    }

    public long getTotalResume() {
        return totalResume;
    }

    public void setTotalResume(long totalResume) {
        this.totalResume = totalResume;
    }

    public long getTotalAts() {
        return totalAts;
    }

    public void setTotalAts(long totalAts) {
        this.totalAts = totalAts;
    }

    public int getLatestScore() {
        return latestScore;
    }

    public void setLatestScore(int latestScore) {
        this.latestScore = latestScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

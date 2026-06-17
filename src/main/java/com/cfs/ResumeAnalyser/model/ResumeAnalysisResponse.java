package com.cfs.ResumeAnalyser.model;

import java.util.List;

public class ResumeAnalysisResponse {

    private List<String> keySkills;
    private int resumeQuality;
    private List<String> improvements;
    private String summary;

    public ResumeAnalysisResponse() {
    }

    public List<String> getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(List<String> keySkills) {
        this.keySkills = keySkills;
    }

    public int getResumeQuality() {
        return resumeQuality;
    }

    public void setResumeQuality(int resumeQuality) {
        this.resumeQuality = resumeQuality;
    }

    public List<String> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
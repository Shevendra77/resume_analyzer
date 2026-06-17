package com.cfs.ResumeAnalyser.dto;

import java.util.List;

public class ResumeAnalysisResponse {

    private Integer score;

    private List<String> strengths;

    private List<String> suggestions;

    public ResumeAnalysisResponse() {
    }

    public ResumeAnalysisResponse(Integer score,
                                  List<String> strengths,
                                  List<String> suggestions) {
        this.score = score;
        this.strengths = strengths;
        this.suggestions = suggestions;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
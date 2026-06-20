package com.cfs.ResumeAnalyser.repository;

import com.cfs.ResumeAnalyser.model.ResumeAnalysis;
import com.cfs.ResumeAnalyser.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {

    List<ResumeAnalysis> findByUser(User user);

    long countByUser(User user);

    List<ResumeAnalysis>
    findByUserAndFilenameContainingIgnoreCase(
            User user,
            String keyword
    );


}

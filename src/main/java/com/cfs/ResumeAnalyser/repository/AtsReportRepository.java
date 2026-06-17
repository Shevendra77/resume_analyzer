package com.cfs.ResumeAnalyser.repository;

import com.cfs.ResumeAnalyser.model.AtsReport;
import com.cfs.ResumeAnalyser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtsReportRepository
        extends JpaRepository<AtsReport, Long> {

    long countByUser(User user);

    List<AtsReport> findByUser(User user);

    List<AtsReport> findByUserOrderByCreatedAtDesc(User user);
}
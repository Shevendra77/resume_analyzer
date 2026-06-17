
        package com.cfs.ResumeAnalyser.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "USER";

    @OneToMany(mappedBy = "user")
    private List<ResumeAnalysis> resumeAnalyses;

    @OneToMany(mappedBy = "user")
    private List<AtsReport> atsReports;

    public User() {
    }

    public User(
            String name,
            String email,
            String password,
            String role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    // NAME

    public String getName() {
        return name;
    }

    public void setName(
            String name) {

        this.name = name;
    }

    // EMAIL

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email) {

        this.email = email;
    }

    // PASSWORD

    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password) {

        this.password = password;
    }

    // ROLE

    public String getRole() {
        return role;
    }

    public void setRole(
            String role) {

        this.role = role;
    }

    // RESUME ANALYSES

    public List<ResumeAnalysis> getResumeAnalyses() {
        return resumeAnalyses;
    }

    public void setResumeAnalyses(
            List<ResumeAnalysis> resumeAnalyses) {

        this.resumeAnalyses = resumeAnalyses;
    }

    // ATS REPORTS

    public List<AtsReport> getAtsReports() {
        return atsReports;
    }

    public void setAtsReports(
            List<AtsReport> atsReports) {

        this.atsReports = atsReports;
    }
}

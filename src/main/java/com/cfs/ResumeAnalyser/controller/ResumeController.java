package com.cfs.ResumeAnalyser.controller;

import com.cfs.ResumeAnalyser.model.AtsReport;
import com.cfs.ResumeAnalyser.model.ResumeAnalysis;
import com.cfs.ResumeAnalyser.model.User;

import com.cfs.ResumeAnalyser.repository.AtsReportRepository;
import com.cfs.ResumeAnalyser.repository.ResumeAnalysisRepository;
import com.cfs.ResumeAnalyser.repository.UserRepository;

import com.cfs.ResumeAnalyser.service.EmailService;
import com.cfs.ResumeAnalyser.service.GeminiService;
import com.cfs.ResumeAnalyser.service.PdfService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tika.Tika;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    private final GeminiService geminiService;
    private final PdfService pdfService;
    private final EmailService emailService;

    private final ResumeAnalysisRepository resumeRepository;
    private final AtsReportRepository atsRepository;
    private final UserRepository userRepository;

    private final Tika tika = new Tika();

    public ResumeController(
            GeminiService geminiService,
            PdfService pdfService,
            EmailService emailService,
            ResumeAnalysisRepository resumeRepository,
            AtsReportRepository atsRepository,
            UserRepository userRepository) {

        this.geminiService = geminiService;
        this.pdfService = pdfService;
        this.emailService = emailService;

        this.resumeRepository = resumeRepository;
        this.atsRepository = atsRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/analyze")
    public Object analyser(
            @RequestParam("file") MultipartFile file)
            throws Exception {

        String content =
                tika.parseToString(
                        file.getInputStream());

        String prompt = """
                Analyze this resume.

                %s

                Return ONLY valid JSON.

                {
                  "keySkills": [],
                  "resumeQuality": 0,
                  "improvements": [],
                  "summary": ""
                }
                """.formatted(content);

        String aiResponse =
                geminiService.generate(prompt);


        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();


        ResumeAnalysis analysis =
                new ResumeAnalysis(
                        file.getOriginalFilename(),
                        aiResponse);

        analysis.setUser(user);

        resumeRepository.save(analysis);

        return aiResponse;
    }
    @PostMapping("/ats-check")
    public ResponseEntity<Map<String, Object>> atsCheck(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jd") String jd) throws Exception {

        String resume = tika.parseToString(file.getInputStream());

        String prompt = """
        Compare Resume and Job Description.

        Resume:
        %s

        Job Description:
        %s

        Return ONLY JSON:
        {
          "atsScore": 0,
          "matchedKeywords": [],
          "missingKeywords": [],
          "summary": ""
        }
        """.formatted(resume, jd);

        String aiResponse = geminiService.generate(prompt);

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        AtsReport report = new AtsReport(aiResponse, jd);
        report.setUser(user);
        atsRepository.save(report);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = mapper.readValue(aiResponse, Map.class);

        return ResponseEntity.ok(json);
    }
  @GetMapping("/my-history")
    public List<ResumeAnalysis> getMyHistory() {


        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                          .findByEmail(email)
                        .orElseThrow();

        return resumeRepository.findByUser(user);
    }

    @GetMapping("/my-ats-history")
    public List<AtsReport> getMyAtsHistory() {


        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();


        return atsRepository.findByUser(user);
    }

    @GetMapping("/all-history")
    public List<ResumeAnalysis> getAllHistory() {

        return resumeRepository.findAll();
    }

    @GetMapping("/all-ats-history")
    public List<AtsReport> getAllAtsHistory() {

        return atsRepository.findAll();
    }

    @PostMapping("/download-report")
    public ResponseEntity<byte[]> downloadReport(
            @RequestParam(value = "content", required = false) String content) {

        if (content == null || content.isEmpty()) {
            content = "No Content Provided";
        }

        byte[] pdf = pdfService.generatePdf(
                "Resume Analysis Report",
                content
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename("resume-report.pdf")
                        .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }
    @PostMapping("/send-report")
    public String sendReport(
            @RequestParam("email") String email,
            @RequestParam("content") String content) {

        emailService.sendReport(
                email,
                content);

        return "Report sent successfully";
    }

    @GetMapping("/search-history")
    public List<ResumeAnalysis> searchResumeHistory(
            @RequestParam String keyword) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return resumeRepository
                .findByUserAndFilenameContainingIgnoreCase(
                        user,
                        keyword
                );
    }
    @GetMapping("/search-ats-history")
    public List<AtsReport> searchAtsHistory(
            @RequestParam String keyword) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return atsRepository
                .findByUserAndJobDescriptionContainingIgnoreCase(
                        user,
                        keyword
                );
    }


}
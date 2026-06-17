package com.cfs.ResumeAnalyser.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReport(String toEmail, String report) {

        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("📊 ResumeAI - ATS Analysis Report");

            String htmlContent = buildHtmlEmail(report);

            helper.setText(htmlContent, true); // ✅ HTML ENABLED

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed", e);
        }
    }
    private String buildHtmlEmail(String report) {

        return """
    
    <div style="font-family:Arial;background:#f4f6f8;padding:20px">

        <div style="background:#1e293b;color:white;padding:20px;border-radius:10px">
            <h2>ResumeAI ATS Report</h2>
        </div>

        <div style="background:white;margin-top:20px;padding:20px;border-radius:10px">

            <h3 style="color:#2563eb">📄 Your Resume Analysis</h3>

            <pre style="font-size:14px;line-height:1.6;color:#333">
""" + report + """
            </pre>

        </div>

        <div style="text-align:center;margin-top:20px;color:#666">
            Powered by ResumeAI 🚀
        </div>

    </div>

    """;
    }
}
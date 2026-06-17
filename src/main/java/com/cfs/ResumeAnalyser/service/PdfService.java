package com.cfs.ResumeAnalyser.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePdf(String title, String content) {

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(document, out);

            document.open();

            // ================= FONT STYLES =================
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);

            // ================= HEADER =================
            Paragraph header = new Paragraph("RESUMEAI - ATS ANALYSIS REPORT", titleFont);
            header.setAlignment(Element.ALIGN_CENTER);
            header.setSpacingAfter(15);
            document.add(header);

            // ================= SCORE BOX =================
            PdfPTable scoreTable = new PdfPTable(1);
            scoreTable.setWidthPercentage(100);

            PdfPCell scoreCell = new PdfPCell(new Phrase("ATS SCORE: 75/100", headerFont));
            scoreCell.setPadding(15);
            scoreCell.setBackgroundColor(new BaseColor(230, 240, 255));
            scoreCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            scoreTable.addCell(scoreCell);
            document.add(scoreTable);

            document.add(Chunk.NEWLINE);

            // ================= CONTENT SECTION =================
            Paragraph sectionTitle = new Paragraph("ANALYSIS REPORT", headerFont);
            sectionTitle.setSpacingAfter(10);
            document.add(sectionTitle);

            Paragraph body = new Paragraph(content, normalFont);
            body.setLeading(18);
            document.add(body);

            document.add(Chunk.NEWLINE);

            // ================= FOOTER =================
            Paragraph footer = new Paragraph(
                    "This report is generated automatically by ResumeAI AI Engine.\n" +
                            "Confidential Document - For recruitment use only.",
                    new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY)
            );

            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
package com.cfs.ResumeAnalyser.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GeminiService() {
        this.webClient = WebClient.builder().build();
    }

    public String generate(String prompt) {

        Map<String, Object> requestBody = Map.of(
                "contents",
                List.of(
                        Map.of(
                                "parts",
                                List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        String response;

        try {
            response = webClient.post()
                    .uri(apiUrl + "?key=" + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

        } catch (Exception e) {
            return fallbackJson("AI service error");
        }

        if (response == null || response.isEmpty()) {
            return fallbackJson("Empty response from Gemini");
        }

        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode textNode = root.path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text");

            String raw = textNode.asText();

            // 🔥 CLEAN AI OUTPUT (VERY IMPORTANT)
            raw = raw.replace("```json", "")
                    .replace("```", "")
                    .replace("\n", " ")
                    .trim();

            return raw;

        } catch (Exception e) {
            return fallbackJson("Response parsing failed");
        }
    }

    // 🔥 STANDARDIZED FALLBACK RESPONSE (MATCH ATS FORMAT)
    private String fallbackJson(String msg) {
        return """
        {
          "atsScore": 0,
          "matchedKeywords": [],
          "missingKeywords": [],
          "summary": "%s"
        }
        """.formatted(msg);
    }
}
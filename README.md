# 📄 AI Resume Analyzer — Spring Boot Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-success)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange)
![AI](https://img.shields.io/badge/AI-Powered-purple)
![License](https://img.shields.io/badge/License-MIT-green)

---

🚀 **An AI-powered Resume Analyzer system that evaluates resumes against job descriptions and provides ATS score, skill matching, and improvement suggestions. Built using Spring Boot + AI APIs.**

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Architecture](#project-architecture)
- [Database Schema](#database-schema)
- [API Reference](#api-reference)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Workflow](#workflow)
- [Screenshots](#screenshots)
- [Future Scope](#future-scope)

---

## ✨ Features <a name="features"></a>

📄 Resume Upload (PDF/DOCX Support)  

🧠 AI-Based Resume Analysis  

📊 ATS Score Generation (0–100)  

🔍 Skill Matching (Resume vs Job Description)  

⚡ Instant Feedback & Suggestions  

📁 Analysis History Tracking  

👤 User Authentication (if implemented)  

---

## 🛠️ Tech Stack <a name="tech-stack"></a>

| Technology       | Purpose                  |
|------------------|--------------------------|
| Java 17+         | Backend Language         |
| Spring Boot      | Backend Framework        |
| Spring Data JPA  | Database ORM             |
| Hibernate        | JPA Implementation       |
| MySQL            | Database                 |
| REST APIs        | Communication Layer      |
| Gemini/OpenAI API| AI Analysis Engine      |
| Maven            | Build Tool               |

---

## 🏗️ Project Architecture <a name="project-architecture"></a>

```text
resume_analyzer/
├── src/
│   └── main/
│       ├── java/com/resumeanalyzer/
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── model/
│       │   ├── dto/
│       │   └── ResumeAnalyzerApplication.java
│       │
│       └── resources/
│           └── application.properties
│
└── pom.xml

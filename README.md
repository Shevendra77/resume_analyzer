# 📄 ResumeAI — AI Powered Resume Analyzer & ATS Checker

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-success)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange)
![JWT](https://img.shields.io/badge/JWT-Authentication-yellow)
![Gemini AI](https://img.shields.io/badge/Gemini-AI-purple)
![License](https://img.shields.io/badge/License-MIT-green)

---

🚀 **ResumeAI** is a full-stack AI-powered Resume Analysis and ATS Checking platform built using Spring Boot, JWT Authentication, Gemini AI, MySQL, HTML, CSS, and JavaScript.

It helps users analyze resumes, check ATS compatibility against job descriptions, generate AI-powered reports, and manage analysis history.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Reference](#api-reference)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [User Flow](#user-flow)
- [Screenshots](#screenshots)
- [Author](#author)
- [License](#license)

---

## ✨ Features <a name="features"></a>

🔐 **Secure Authentication** — User registration and login powered by JWT-based authentication with Spring Security.

📄 **AI Resume Analysis** — Upload resumes in PDF, DOC, or DOCX format and receive comprehensive AI-generated analysis reports.

📊 **Resume Quality Assessment** — Evaluate resume effectiveness through an intelligent scoring system and actionable feedback.

🧠 **Key Skills Extraction** — Automatically identify and highlight technical, professional, and domain-specific skills.

💡 **Personalized Improvement Suggestions** — Receive AI-driven recommendations to enhance resume quality and recruiter appeal.

📝 **Professional Resume Summary** — Generate concise and impactful summaries tailored to the uploaded resume.

🎯 **ATS Compatibility Checker** — Compare resumes against job descriptions to measure Applicant Tracking System (ATS) compatibility.

📈 **ATS Score Calculation** — Analyze resume-job matching and generate an ATS compatibility score.

✅ **Matched Keywords Detection** — Identify keywords and skills successfully aligned with job requirements.

❌ **Missing Keywords Analysis** — Highlight missing keywords and competencies that can improve ATS performance.

📚 **Resume Analysis History** — Access and review all previously generated resume analysis reports.

📊 **ATS Report History** — Store and manage ATS evaluation reports for future reference.

🔍 **Advanced Search Functionality** — Quickly search and filter resume and ATS history records.

📧 **Email Report Delivery** — Send AI-generated reports directly to email with a single click.

📄 **PDF Report Export** — Download detailed analysis reports in a professional PDF format.

👤 **Interactive User Dashboard** — Monitor activity, analytics, and report statistics from a centralized dashboard.

📌 **Latest Performance Tracking** — Instantly view the most recent ATS score and analysis results.

🔒 **Enterprise-Grade Security** — Secure backend APIs using Spring Security and role-based access control.

🤖 **Gemini AI Integration** — Leverage Google's Gemini AI for intelligent resume analysis and ATS evaluation.

📱 **Responsive User Interface** — Optimized experience across desktops, tablets, and mobile devices.

⚡ **Real-Time Processing** — Generate resume analysis and ATS reports instantly with fast AI-powered processing.

---

## 🛠️ Tech Stack <a name="tech-stack"></a>

| Technology | Purpose |
|------------|----------|
| Java 17 | Backend Language |
| Spring Boot | Backend Framework |
| Spring Security | Security |
| JWT | Authentication |
| Spring Data JPA | ORM |
| Hibernate | Database Layer |
| MySQL | Database |
| Gemini AI | Resume Analysis |
| Apache Tika | Resume Parsing |
| HTML | Frontend |
| CSS | Styling |
| JavaScript | Frontend Logic |

---
## 📁 Project Structure <a name="project-structure"></a>

```text
ResumeAnalyser
│
├── .idea
├── .mvn
│
├── src
│   └── main
│       └── java
│           └── com.cfs.ResumeAnalyser
│
│               ├── config
│               │   └── CorsConfig
│               │
│               ├── controller
│               │   ├── AdminController
│               │   ├── AuthController
│               │   ├── ResumeController
│               │   └── UserController
│               │
│               ├── dto
│               │   ├── DashboardResponse
│               │   ├── DashboardStats
│               │   ├── LoginRequest
│               │   ├── LoginResponse
│               │   ├── RegisterRequest
│               │   └── ResumeAnalysisResponse
│               │
│               ├── exception
│               │
│               ├── model
│               │   ├── AtsReport
│               │   ├── ResumeAnalysis
│               │   ├── ResumeAnalysisResponse
│               │   └── User
│               │
│               ├── repository
│               │   ├── AtsReportRepository
│               │   ├── ResumeAnalysisRepository
│               │   └── UserRepository
│               │
│               ├── security
│               │   ├── JwtAuthenticationFilter
│               │   ├── JwtUtil
│               │   └── SecurityConfig
│               │
│               ├── service
│               │   ├── EmailService
│               │   ├── GeminiService
│               │   ├── PdfService
│               │   └── ResumeService
│               │
│               └── ResumeAnalyserApplication
│
├── frontend
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── user-dashboard.html
│   ├── resume-analyzer.html
│   ├── ats-checker.html
│   ├── resume-history.html
│   ├── ats-history.html
│   ├── about.html
│   │
│   ├── css
│   │   └── style.css
│   │
│   └── js
│       ├── login.js
│       ├── register.js
│       ├── user.js
│       ├── resume-analyzer.js
│       ├── ats-checker.js
│       ├── resume-history.js
│       └── ats-history.js
│
├── pom.xml
└── README.md
```

## 🔗 API Reference <a name="api-reference"></a>

### Authentication

```http
POST /auth/register
POST /auth/login
```

### Resume Analysis

```http
POST /api/resume/analyze
GET  /api/resume/my-history
GET  /api/resume/search-history
```

### ATS Checker

```http
POST /api/resume/ats-check
GET  /api/resume/my-ats-history
GET  /api/resume/search-ats-history
```

### Dashboard

```http
GET /api/user/dashboard
```

---

## 🚀 Getting Started <a name="getting-started"></a>

### Clone Repository

```bash
git clone https://github.com/Shevendra77/resume_analyzer.git
```

### Create Database

```sql
CREATE DATABASE resume_analyser;
```

### Configure Application

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resume_analyser
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

gemini.api.key=YOUR_API_KEY
```

---

## ▶ Running the Application <a name="running-the-application"></a>

### Backend

```bash
mvn spring-boot:run
```

Runs on:

```text
http://localhost:8080
```

### Frontend

```text
Open With Live Server
```

Runs on:

```text
http://localhost:5500
```

---

## 👨‍💻 User Flow <a name="user-flow"></a>

```text
Register
   ↓
Login
   ↓
Dashboard
   ↓
Resume Analyzer
   ↓
Resume History
   ↓
ATS Checker
   ↓
ATS History
   ↓
Logout
```

---

## 📸 Screenshots <a name="screenshots"></a>

- Home Page
- Login Page
- Register Page
- User Dashboard
- Resume Analyzer
- ATS Checker
- Resume History
- ATS History

(Add screenshots here)

---

## 👨‍💻 Author <a name="author"></a>

### Shevendra Chandel

📧 Email: shevendrachandel@gmail.com

💻 GitHub: https://github.com/Shevendra77


---

## 📄 License <a name="license"></a>

This project is licensed under the MIT License.

---

⭐ If you found this project helpful, please give it a star on GitHub.

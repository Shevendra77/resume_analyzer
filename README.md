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

🔐 Authentication — Secure user registration and login using JWT Authentication

📄 Resume Analyzer — Upload resumes and get AI-powered analysis reports

📊 Resume Quality Score — Evaluate overall resume quality and effectiveness

🧠 Key Skills Detection — Automatically identify technical and professional skills

💡 Improvement Suggestions — Personalized recommendations to strengthen resumes

📝 Resume Summary — Generate an AI-based professional summary

🎯 ATS Checker — Compare resumes against job descriptions

📈 ATS Score Calculation — Measure resume compatibility with ATS systems

✅ Matched Keywords Detection — Identify keywords matching the job description

❌ Missing Keywords Analysis — Highlight important missing keywords

📚 Resume History — Access all previously generated resume analysis reports

📊 ATS History — View and manage past ATS evaluation reports

🔍 Search History — Search Resume and ATS reports instantly

📧 Email Reports — Send analysis reports directly via email

📄 PDF Export — Download professional PDF reports

👤 User Dashboard — View user analytics and activity

📌 Latest ATS Score — Track most recent ATS performance

🔒 Secure APIs — Protected endpoints using Spring Security

🤖 Gemini AI Integration — AI-powered resume analysis

📱 Responsive UI — Mobile-friendly modern interface

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
git clone https://github.com/Shevendra77/ResumeAI.git
```

### Create Database

```sql
CREATE DATABASE resume_ai;
```

### Configure Application

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resume_ai
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

🔗 LinkedIn: Add Your LinkedIn Profile

---

## 📄 License <a name="license"></a>

This project is licensed under the MIT License.

---

⭐ If you found this project helpful, please give it a star on GitHub.

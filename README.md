# Quizz App - Microservices Based Architecture

This project is a **Microservices-based Quiz Application** developed using Spring Boot. It demonstrates core microservices concepts such as service discovery, inter-service communication using Feign, centralized routing through an API Gateway, and interaction with a MySQL database using Spring Data JPA.

---

## 🧱 Microservices Structure

### 1. **Question-Service**
- Manages questions-related data.
- Exposes APIs to CRUD questions.
- Connected to MySQL using Spring Data JPA.

### 2. **Quizz-Service**
- Manages quizzes and quiz generation.
- Fetches questions from the Question-Service using OpenFeign.
- Handles quiz logic, including wrapping questions and evaluating responses.

### 3. **Service-Registry**
- Eureka Server for service discovery.
- Registers all microservices dynamically.

### 4. **API-Gateway**
- Central entry point for all client requests.
- Routes traffic to corresponding services.
- Provides basic abstraction and potential for load balancing/security.

---

## 🚀 Technologies & Dependencies

- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Cloud Eureka Server / Eureka Client**
- **Spring Cloud OpenFeign**
- **MySQL Database**

---

## 🏗️ Project Setup

### Prerequisites:
- Java 17+
- Maven
- MySQL

### Steps:

1. **Clone the repository**
   ```bash
   git clone https://github.com/kasatejaswarreddy0512/Quizz_App_MicroServices

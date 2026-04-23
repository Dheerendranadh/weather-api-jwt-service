
# 🌦️ Weather API with JWT Security

## 📌 Project Overview

This is a Spring Boot backend application that provides secure REST APIs for managing weather data using JWT-based authentication and authorization.

---

## 🚀 Features

* 🔐 JWT-based Authentication & Authorization
* 🌍 REST APIs for weather data
* 🧱 Layered architecture (Controller, Service, Repository)
* 📦 Exception handling with proper responses

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security (JWT)
* Hibernate / JPA
* MySQL
* Maven

---

## 📂 Project Structure

src/
├── controller/
├── service/
├── repository/
├── dto/
├── security/

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

git clone https://github.com/Dheerendranadh/weather-api-jwt-service.git

### 2️⃣ Navigate to project

cd weather-api-jwt-service

### 3️⃣ Configure database

Update application.properties with your DB credentials

### 4️⃣ Run the application

Run main class or:
mvn spring-boot:run

---

## 🔗 API Endpoints

### 🌦️ Weather

- **POST/weather** — Create a new weather entry  
- **GET /weather/{id}** — Retrieve a weather entry by ID  
- **PUT /weather/{id}** — Update a weather entry by ID  
- **DELETE /weather/{id}** — Delete a weather entry by ID

### 🔐 Auth

- POST /authenticate — Authenticate and obtain a token

---

## 📖 API Testing

Use Postman to test APIs

---

## 💡 Future Improvements

* 🤖 Add AI-powered weather summaries
* 📘 Add Swagger API documentation
* ☁️ Deploy to cloud (AWS)
---

## 👨‍💻 Author

Dheerendranadh Sunkavalli

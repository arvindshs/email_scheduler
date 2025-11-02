# 📧 Email Scheduler & Admin Dashboard

A production-ready **Spring Boot + MySQL + JavaMail** project for automating email scheduling, sending, and management — with a sleek frontend dashboard.

---

## ✨ Features
- Automated email sending with JavaMailSender.
- REST endpoints for managing emails.
- Admin panel (HTML + CSS + JS) for fetching & deleting mails.
- MySQL integration for email logs.
- Secure credentials in `application.properties`.

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot (Java 17)
- **Frontend:** HTML, CSS, Vanilla JS
- **Database:** MySQL
- **Email:** JavaMail (SMTP)
- **Build Tool:** Maven

---

## 🚀 Setup Instructions

### 1️⃣ Prerequisites
- Java 21+
- Maven 3.9+
- MySQL running locally or remotely

---

### 2️⃣ Clone the Repository
```bash
git clone https://github.com/arvindshs/email_scheduler.git
cd email_scheduler

💾 Install Maven Dependencies
mvn clean install

🧩 2. Configure application.properties

Create or update the file at:
src/main/resources/application.properties

# ========================
# 💌 MAIL CONFIGURATION
# ========================
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=youremail@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# ========================
# 🗄️ DATABASE CONFIGURATION
# ========================
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dial
# ========================
# 🌐 SERVER CONFIG
# ========================
server.port=8080
🧰 3. Run the Project
Using Maven:
mvn spring-boot:run


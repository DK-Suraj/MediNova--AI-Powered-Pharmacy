# 💊 Pharmacy Management System

A full-stack **Pharmacy Web Application** built using **Java, Spring Boot, and MySQL**.
This project allows users to browse medicines, search products, manage cart, and simulate checkout functionality.

---

## 🚀 Features

### 👤 User Module

* User Registration & Login (Session-based)
* View all medicines
* Search medicines (Normal + AI-based search)
* Add to Cart
* Update quantity (increase/decrease)
* Remove items from cart
* Logout functionality

### 🛒 Cart & Checkout

* Add medicines to cart
* Auto quantity update if item already exists
* Calculate total price
* Checkout process (basic implementation)

### 🔍 Search System

* Keyword-based medicine search
* AI-style search API (dynamic filtering)

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Frontend:** Thymeleaf, HTML, CSS, Bootstrap
* **Database:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **Build Tool:** Maven
* **Server:** Tomcat (Embedded)

---

## 📁 Project Structure

```
com.pharmacy
│
├── controller       # Handles HTTP requests
├── model            # Entity classes (User, Medicine, Cart)
├── repository       # JPA Repositories
├── service          # Business logic (if added)
└── resources
    ├── templates    # Thymeleaf HTML pages
    └── application.properties
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/pharmacy-management.git
cd pharmacy-management
```

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pharmacy_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Run Project

```bash
mvn spring-boot:run
```

OR run from IDE (IntelliJ / Eclipse)

---

## 🌐 Application URLs

* Home: http://localhost:8080/user/home
* Login: http://localhost:8080/user/login
* Register: http://localhost:8080/user/register
* Medicines: http://localhost:8080/user/medicines
* Cart: http://localhost:8080/cart/view

---

## 🧠 Future Enhancements

* Payment Gateway Integration 💳
* Order History 📦
* Admin Dashboard 👨‍💼
* JWT Authentication 🔐
* Email Notifications 📧

---

## 📸 Screenshots

<img width="1901" height="859" alt="Screenshot 2026-03-19 150026" src="https://github.com/user-attachments/assets/52ba2243-e2a5-42d2-b373-0f4b5d11d3a9" />
<img width="1905" height="600" alt="Screenshot 2026-03-19 150220" src="https://github.com/user-attachments/assets/1df70313-565b-4c66-beb7-cd5855803d61" />
<img width="1077" height="646" alt="admin-login" src="https://github.com/user-attachments/assets/173cfad4-c01b-4c62-8d8c-7f2575cf1e9f" />
<img width="1812" height="566" alt="Screenshot 2026-03-19 151014" src="https://github.com/user-attachments/assets/6db35478-a7f3-4174-88ea-7619dbdf1dd9" />
<img width="1774" height="783" alt="Screenshot 2026-03-19 151143" src="https://github.com/user-attachments/assets/5bc8aba6-46bf-4540-8ff8-5be6b2c48aad" />
<img width="1737" height="767" alt="Screenshot 2026-03-19 151049" src="https://github.com/user-attachments/assets/a00c2039-5713-435c-ba9e-ef7d0c75d006" />
<img width="1919" height="870" alt="Screenshot 2026-03-19 150307" src="https://github.com/user-attachments/assets/cd51691c-c1ca-451f-ab9b-4509ee071990" />
<img width="1839" height="820" alt="Screenshot 2026-03-19 150409" src="https://github.com/user-attachments/assets/f8ed8856-0fd4-497c-8b99-e6f90a2db73d" />
<img width="1871" height="874" alt="Screenshot 2026-03-19 150510" src="https://github.com/user-attachments/assets/dd7ac216-c4df-4c39-9911-c52e80be866d" />
<img width="1837" height="643" alt="Screenshot 2026-03-19 150550" src="https://github.com/user-attachments/assets/24cfcb98-8e28-4117-9fe5-8b21160322aa" />
<img width="1639" height="848" alt="Screenshot 2026-03-19 150646" src="https://github.com/user-attachments/assets/b8d9ba8f-79c4-40ce-a6af-50bd7f22ea78" />
<img width="1765" height="501" alt="Screenshot 2026-03-19 150838" src="https://github.com/user-attachments/assets/2c57244f-1895-465b-a731-023f108f6f27" />

---

## 👨‍💻 Author

**Suraj Dhakne**

* Java Developer (Fresher)
* Skilled in Spring Boot, MySQL, REST APIs

---

## ⭐ GitHub

If you like this project, give it a ⭐ on GitHub!

---

## 📌 Notes

* This project uses **Session-based Authentication (No Spring Security)**
* Designed for learning and interview demonstration purposes

---

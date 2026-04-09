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

*Add your project screenshots here*

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

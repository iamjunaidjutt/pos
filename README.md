# POS Pharmacy System 💊

A desktop point-of-sale (POS) and inventory management application designed for community pharmacies, developed in Java using Hibernate 5.6 ORM and MySQL.

---

## Overview

The **POS Pharmacy System** streamlines sales transactions, inventory management, and prescription tracking for retail pharmacies. Built using Java with JPA/Hibernate for object-relational mapping, it handles database transactions with ACID compliance, automated schema synchronization, and connection pooling.

### Key Features

- **Point-of-Sale (POS) Checkout**: Fast sales entry with itemized billing, pricing calculation, and transaction commits.
- **Inventory & Batch Tracking**: Models pharmaceutical inventory with batch numbers and expiration dates to support First-Expiring-First-Out (FEFO) dispensing.
- **User Authentication & Roles**: Role-based access control (`User` entity) with secure credentials for cashiers, pharmacists, and store managers.
- **Hibernate ORM Persistence**: Managed via JPA `RESOURCE_LOCAL` persistence unit (`pu`) with automated MySQL schema updates (`hbm2ddl.auto = update`).
- **Connection Pooling**: Integrated C3P0 connection pool for low-latency database connection reuse.

---

## Architecture & Tech Stack

```
pos/
├── src/
│   ├── main/
│   │   ├── java/com/scd/
│   │   │   ├── App.java                # Application entry point & JPA lifecycle
│   │   │   └── Models/
│   │   │       └── User.java           # JPA Entity model
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml     # JPA Persistence Unit & Hibernate config
│   └── test/java/com/scd/              # JUnit test cases
├── pom.xml                             # Maven build configuration
└── README.md
```

- **Language & Runtime**: Java 8+ / SE
- **ORM / Persistence**: Hibernate Core 5.6.6.Final, Hibernate Entity Manager, Hibernate Ehcache
- **Database**: MySQL 8.0 via `mysql-connector-java` 8.0.27
- **Connection Pool**: C3P0 connection pool
- **Build Tool**: Apache Maven 3.x
- **Testing**: JUnit 4.11

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Apache Maven**: Version 3.6+
- **MySQL Server**: Version 8.0+

### Database Setup

1. Start your local MySQL server.
2. Create the project database:
   ```sql
   CREATE DATABASE scd_project;
   CREATE USER 'scd_admin'@'localhost' IDENTIFIED BY 'scd13579';
   GRANT ALL PRIVILEGES ON scd_project.* TO 'scd_admin'@'localhost';
   FLUSH PRIVILEGES;
   ```
   *(Or modify the JDBC credentials in `src/main/resources/META-INF/persistence.xml` to match your local setup).*

### Build and Run

1. Clone the repository:
   ```bash
   git clone git@github.com:iamjunaidjutt/pos.git
   cd pos
   ```

2. Compile and package the application with Maven:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.scd.App"
   ```

---

## License

This project was developed for educational and academic coursework at FAST-NUCES.

# Blood Donation Management System

MVC-based Java 17 web application for donor-patient matching and notification handling.

## Tech Stack
- Java 17
- JSP, Servlets, JDBC
- MySQL
- Maven
- Apache Tomcat
- Bootstrap 5, HTML, CSS, JavaScript

## Project Structure
- `src/main/java/com/bloodbank/controller` servlet controllers
- `src/main/java/com/bloodbank/dao` JDBC access layer
- `src/main/java/com/bloodbank/model` domain objects
- `src/main/java/com/bloodbank/service` business services
- `src/main/java/com/bloodbank/util` DB, password, and validation helpers
- `src/main/java/com/bloodbank/filter` authentication filter
- `src/main/java/com/bloodbank/listener` startup bootstrap
- `src/main/webapp` JSP pages, CSS, and JavaScript

## Database Setup
1. Create the schema using `src/main/resources/schema.sql`.
2. Update MySQL credentials in `src/main/resources/db.properties`.

## Default Admin
- Username: `admin`
- Password: `admin123`

The default admin is created automatically at application startup if no admin records exist.

## Build
```bash
mvn clean package
```

## Deploy
1. Copy the generated `war` to Tomcat `webapps`.
2. Start Tomcat.
3. Open the application in the browser.

## Workflow Summary
- Donors register as `PENDING`.
- Admin approves donors and patients.
- Patients create blood requests in `PENDING`.
- Admin approves a request and matching approved donors are notified automatically.
- Donors accept or reject requests.
- Patients can view accepted donor contact details.

## Security
- BCrypt password hashing
- Session-based login
- Role-based authorization filter
- PreparedStatements for all database access
- Server-side input validation


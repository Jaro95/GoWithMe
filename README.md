
# Project Description

Web application that allows people with similar interests to meet

## Technologies

- **Java 17**
- **Spring Boot 2.7.16**
- **Spring Boot Security**
- **Spring Boot WebSocket**
- **Spring Boot Mail**
- **MySQL**
- **JSP (for the view layer)**
- **Maven (for dependency management)**
## Features

- User registration (confirmation by e-mail)
- Login and logout
- User roles and permissions
- Securing application resources
- Managing user data with MySQL
- Chat between users using WebSocket
- CRUD operations for finding people for activities
- CRUD operations for user profiles
- Admin panel for managing users
- Schedule to remove users who have not confirmed their registration within 24 hours



## Configuration

### Database

The application uses an embedded MySql database.

### Spring Security

Spring Security is configured to protect application resources. Users and roles are defined in the database.

### Prerequisites

- Java 17 (or later)
- Maven 4.0.0 (or later)
- MySql
- Spring Boot 2.7.16 (or later)

### `application.properties` File

Below are the key configuration properties:
```properties
# MySQL Database Configuration
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/gowithme
spring.datasource.username= add your username
spring.datasource.password= add your password
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true;

# Mail Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=add your username
spring.mail.password=add your password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

If you do not want to use e-mail verification, change the value in UserServiceImpl.class in line 51 to true and in line 54 to "verified".

### Run

Paste link http://localhost:8080/gowithme/admin/create-start you create 2 users(super admin and admin - email and password are in AdminController.class 37-49 lines) and elements necessary for the proper functioning of the application.


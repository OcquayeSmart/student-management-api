# Student Management API

A role-based REST API for managing student records and user accounts, built with **Java 21, Spring Boot 4, Spring Security, JWT and MySQL**.

This is my **first API project**, developed as part of my journey into backend engineering. It gave me practical experience designing RESTful endpoints, implementing authentication and authorization, managing relational data, and building a secure backend application.

## Features

- **Student Management:** Create, retrieve, update and delete student records.
- **User Authentication:** Register and authenticate users using email and password.
- **JWT Authentication:** Secure API requests using JSON Web Tokens.
- **Role-Based Authorization:** Differentiate access for Students, Lecturers and Administrators.
- **Account Provisioning:** Allow administrators to create accounts with elevated roles.
- **Password Security:** Hash passwords using BCrypt.
- **Input Validation:** Validate incoming requests using Jakarta Bean Validation.
- **Database Migrations:** Manage database schema changes with Flyway.
- **Student Account Association:** Maintain a relationship between authentication accounts and academic records.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot 4.1.2 | Application framework |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| MySQL 8 | Relational database |
| Flyway | Database migrations |
| JWT / OAuth2 Resource Server | Token-based authentication |
| MapStruct | DTO mapping |
| Lombok | Boilerplate reduction |
| Maven | Dependency and build management |

## Architecture

The application follows a layered structure:

```text
com.smartocquaye.studentmanagementapi
│
├── controllers
├── dtos
├── entities
├── mappers
├── repositories
├── security
└── services
```

Each layer has a defined responsibility:

- **Controllers:** Handle HTTP requests and responses.
- **Services:** Contain application business logic.
- **Repositories:** Handle database operations.
- **Entities:** Represent the application's database model.
- **DTOs:** Define data exchanged through the API.
- **Mappers:** Convert between entities and DTOs.
- **Security:** Handle authentication, JWT processing and authorization.

## Authentication & Authorization

The API uses stateless, token-based authentication.

1. A user registers with an email address and password.
2. The password is hashed using BCrypt before being stored.
3. The user authenticates with their credentials.
4. The API issues a signed JWT.
5. Subsequent protected requests include the token in the `Authorization` header.
6. Spring Security validates the token and enforces the user's assigned permissions.

### User Roles

| Role | Access |
|---|---|
| `STUDENT` | Access personal student information. |
| `LECTURER` | Access student management operations. |
| `ADMIN` | Access student management operations and administrative account provisioning. |

Public registration creates student accounts. Administrative accounts are provisioned through a protected administrator endpoint.

**Note:** The application uses Spring Security's OAuth2 Resource Server support for JWT processing. It does not implement a full OAuth2 Authorization Server or third-party OAuth2 login.

## API Endpoints

### Authentication

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/api/auth/register` | Register a student account | Public |
| POST | `/api/auth/login` | Authenticate and obtain a JWT | Public |
| POST | `/api/auth/change-password` | Change account password | Authenticated |

### Student Management

| Method | Endpoint | Description | Access |
|---|---|---|---|
| GET | `/student` | Retrieve all students | Lecturer / Admin |
| GET | `/student/{id}` | Retrieve a student by ID | Lecturer / Admin |
| POST | `/student` | Create a student record | Lecturer / Admin |
| PUT | `/student/{id}` | Update a student record | Lecturer / Admin |
| DELETE | `/student/{id}` | Delete a student record | Lecturer / Admin |
| GET | `/student/me` | Retrieve the authenticated student's record | Student |

### Administration

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/api/admin/accounts` | Create an account with an administrative role | Admin |

Protected endpoints require a valid JWT:

```http
Authorization: Bearer <your-jwt-token>
```

## Database Design

The application separates authentication accounts from academic student records.

- **User:** Stores authentication information, including email, hashed password and role.
- **Student:** Stores academic information, including name, department and level.

A one-to-one relationship connects a student record to its user account. This separation allows the application to support different account roles without treating every user as a student.

Flyway manages schema changes through versioned SQL migrations, while Hibernate validates the entity mappings against the database schema.

## Getting Started

### Prerequisites

- Java 21
- MySQL 8
- Maven
- Git

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/student-management-api.git
cd student-management-api
```

### 2. Create the database

```sql
CREATE DATABASE student;
```

### 3. Configure environment variables

The application expects the following environment variables:

| Variable | Description |
|---|---|
| `DB_PASSWORD` | MySQL database password |
| `JWT_SECRET` | Base64-encoded JWT signing secret |

The JWT secret must decode to at least 32 bytes.

For Windows PowerShell:

```powershell
$env:DB_PASSWORD="your_database_password"
$env:JWT_SECRET="your_base64_encoded_secret"
```

### 4. Build and run

```bash
mvn clean install
mvn spring-boot:run
```

Flyway applies pending database migrations automatically during application startup.

## Project Scope

This project focuses on the fundamentals of backend API development, particularly:

- RESTful API design.
- Layered application architecture.
- Relational database modelling.
- Authentication and authorization.
- JWT-based security.
- Data validation and DTO mapping.
- Database schema versioning.

It is an evolving learning project, with potential improvements including automated integration tests, API documentation with Swagger and deployment.

## Author

**Smart Nii Attey Ocquaye**

Computer Science Student @ Kwame Nkrumah University of Science and Technology (KNUST), Ghana

Aspiring Backend Engineer, focused on building a strong foundation in Java, Spring Boot, software architecture and backend systems.
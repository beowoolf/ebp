# Engineer Book Plus (ebp)

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.16-brightgreen)
![Build](https://img.shields.io/badge/build-Maven-blue)
![Packaging](https://img.shields.io/badge/packaging-WAR-lightgrey)
![Status](https://img.shields.io/badge/status-work%20in%20progress-yellow)
![License](https://img.shields.io/badge/license-not%20specified-red)

A modern rewrite of an engineering/master's thesis application, migrating the original
project from the classic **Spring Framework** to **Spring Boot**.

## Table of Contents

- [Project Description](#project-description)
- [Tech Stack](#tech-stack)
- [Getting Started Locally](#getting-started-locally)
- [Available Scripts](#available-scripts)
- [Project Scope](#project-scope)
- [Project Status](#project-status)
- [License](#license)

## Project Description

**Engineer Book Plus** is a professional social-networking platform for engineers. Users
can create rich profiles, connect with one another, and exchange private messages.

The project has a strong academic character: rather than implementing each feature only
once, it deliberately provides **multiple, interchangeable data-access implementations**
for the same domain (plain JDBC, Hibernate, JPA, Spring `JdbcTemplate`, and MongoDB). This
makes it a practical showcase of different persistence strategies behind a single, common
`Repository` / `Service` abstraction.

Key characteristics:

- Clean layered architecture (`domain` → `repository` → `service`).
- Swappable persistence backends selected via Spring `@Qualifier`.
- Cross-cutting performance monitoring implemented in two ways — an AspectJ **AOP aspect**
  around the repository layer and a Spring MVC **`HandlerInterceptor`** around web requests.
- Use of classic design patterns: generic `Repository`/`Service`, `PreparedStatement`
  "wizards" (builder-style SQL statement factories), row `Mapper`s, and `ResultSet`
  `Extractor`s.

## Tech Stack

| Area | Technology |
| --- | --- |
| Language | Java 25 |
| Framework | Spring Boot 3.4.16 |
| Web | Spring Web MVC (Spring MVC, `ModelAndView`) |
| Security | Spring Security (`UserDetailsService`, `ROLE_USER`) |
| Persistence (SQL) | Spring Data JPA, Hibernate, plain JDBC, Spring `JdbcTemplate` |
| Persistence (NoSQL) | Spring Data MongoDB |
| Mail | Spring Boot Starter Mail |
| Validation | Spring Boot Starter Validation (Jakarta Bean Validation) |
| Boilerplate reduction | Project Lombok |
| Utilities | Apache Commons Lang 2.6 |
| AOP | AspectJ (Spring AOP) |
| Server | Embedded/Provided Apache Tomcat |
| Build tool | Apache Maven (with Maven Wrapper) |
| Packaging | WAR (deployable to an external servlet container) |
| Testing | Spring Boot Starter Test |

## Getting Started Locally

### Prerequisites

- **JDK 25** installed and available on your `PATH`.
- A running **MySQL** database (the SQL entities/statements target a schema/catalog named
  `16120792_nebp`).
- A running **MongoDB** instance (used by the MongoDB repository implementations).
- An **SMTP mail server** (used to send generated passwords and notifications).
- Maven — or simply use the bundled Maven Wrapper (`./mvnw`).

### Clone the repository

```bash
git clone <repository-url>
cd ebp
```

### Configure the application

> **Note:** This repository does not ship a `src/main/resources/application.properties`
> (or `.yml`) file. Before running the application you must create one and provide your own
> connection details for MySQL, MongoDB, and SMTP.

Create `src/main/resources/application.properties` with values similar to:

```properties
# MySQL (JPA / Hibernate / JDBC)
spring.datasource.url=jdbc:mysql://localhost:3306/16120792_nebp
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=none

# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/ebp

# Mail (SMTP)
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your_mail_user
spring.mail.password=your_mail_password
```

### Run the application

Using the Maven Wrapper:

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows (PowerShell / cmd)
.\mvnw.cmd spring-boot:run
```

The application starts on the default port (`http://localhost:8080`).

## Available Scripts

This is a Maven project; the following commands (via the Maven Wrapper) cover the common
workflows:

| Command | Description |
| --- | --- |
| `./mvnw spring-boot:run` | Run the application locally. |
| `./mvnw clean package` | Compile, run tests, and build the deployable WAR into `target/`. |
| `./mvnw test` | Run the test suite. |
| `./mvnw clean install` | Build and install the artifact into the local Maven repository. |
| `./mvnw clean` | Remove build output (`target/`). |

> On Windows use `.\mvnw.cmd` in place of `./mvnw`.

The build produces a WAR file (`www-0.0.1-SNAPSHOT.war`) that can be deployed to an external
servlet container such as Apache Tomcat, or run directly via the Spring Boot Maven plugin.

## Project Scope

The domain model and services currently cover:

- **User profiles (`Person`)** — core account data (username, e-mail, password, name,
  surname, gender, birthday) plus related information:
  - `About`, `Education`, `Experience`, `Achievement`
  - `Language`, `Phone`, `Homepage`, `Skype`, `Gg` (contact details)
- **Registration** — creates a new profile, generates a random password, and e-mails it to
  the user for account activation.
- **Account management** — dedicated "changer" services for updating login, e-mail, and
  password.
- **Messaging (`Message`)** — private conversations between users, retrievable per
  interlocutor.
- **Invitations (`Invitation`)** — sending and receiving connection requests between users.
- **Authentication & authorization** — Spring Security integration via a custom
  `UserDetailsService` (single `ROLE_USER` role).
- **Mail delivery** — sending registration/notification e-mails.
- **Performance monitoring** — request/repository timing via AOP and an MVC interceptor.

### Out of scope / not present

- No REST or MVC controllers are currently included — the project is centered on the
  domain, persistence, and service layers.
- No front-end views (JSP/HTML/Thymeleaf templates) are present.
- No bundled configuration file or database schema/seed scripts.

## Project Status

**Work in progress.** Version `0.0.1-SNAPSHOT`.

This is an actively evolving migration project — a modernized version of the author's
thesis application, being ported from Spring Framework to Spring Boot. Core domain,
persistence, and service layers are implemented; the web/presentation layer and runtime
configuration are still to be added.

## License

No license has been specified for this project yet. Until a license file is added, all
rights are reserved by the author. If you intend to use, modify, or distribute this code,
please contact the author or add an appropriate `LICENSE` file.

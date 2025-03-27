# Short Links Service

This project is a distributed URL shortening service consisting of three components:
- **Gateway** – an API gateway that processes incoming HTTP requests and distributes them between services.
- **URL Shortening Service** – receives original URLs, generates short links, and stores them in the database.
- **Redirection Service** – processes short links and redirects users to the original URLs.

Stack: Java, Spring Boot, Thymeleaf, PostgreSQL, Docker, K8s, HTML+CSS+JS.


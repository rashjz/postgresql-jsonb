# Spring Boot with PostgreSQL, Flyway, and JSONB

This example shows how to create an application using Spring Boot, PostgreSQL, Flyway, and JSONB


This will likely fail. You need to configure a PostgreSQL database with the following settings in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://192.168.99.100:5432/coursedb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create
```

And start PostgreSQL in a Docker container:

```bash
docker pull postgres:11
docker run --name dev-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres:11
# CREATE db coursedb
docker exec dev-postgres psql -U postgres -c"CREATE DATABASE coursedb" postgres
```

## Links


## Help

Please post any questions


# Demo Person Service

A Spring Boot application that stores people in PostgreSQL and exposes a small HTTP API through `PersonController`.

## Technology

- Java 26
- Spring Boot 4.1.0
- Gradle (wrapper included)
- Spring MVC, Spring Data JPA, Thymeleaf, and Spring Security
- PostgreSQL
- JUnit 5 and Mockito

## Prerequisites

- JDK 26
- PostgreSQL running locally

Create a PostgreSQL database named `postgres`, or change the datasource URL to match your database. Configure a local username and password in `src/main/resources/application.properties` before starting the application. Do not commit real database credentials.

The application creates or updates its mapped tables at startup because `spring.jpa.hibernate.ddl-auto=update` is configured.

## Run the application

From the project root:

```powershell
.\gradlew.bat bootRun
```

The application uses Spring Boot's default HTTP port, `8080`, unless it is overridden in configuration.

## Person API

`PersonController` works with the `Person` entity, whose fields are:

| Field | Type | Description |
| --- | --- | --- |
| `personId` | integer | Person identifier; it is supplied by the client. |
| `lastName` | string | Last name. |
| `firstname` | string | First name. |
| `address` | string | Address. |
| `city` | string | City. |
| `occupations` | object | Optional linked occupation. |

### Get all people

```http
GET /getpersons
```

This endpoint retrieves every person, places the collection in the `persons` view-model attribute, and renders the `HelloWorld` Thymeleaf template as an HTML table. It is an HTML response rather than a JSON API response.

### Get one person

```http
GET /getpersons/{id}
```

Example:

```powershell
Invoke-RestMethod http://localhost:8080/getpersons/1
```

The response body is the matching `Person`, or `null` when the service does not find that ID.

### Create a person

```http
POST /newperson
Content-Type: application/json
```

Example:

```powershell
$body = @{
  personId = 1
  lastName = 'Doe'
  firstname = 'Jane'
  address = '10 Main Street'
  city = 'London'
} | ConvertTo-Json

Invoke-RestMethod http://localhost:8080/newperson -Method Post -ContentType 'application/json' -Body $body
```

On success, the controller returns HTTP `201 Created`, the saved person as JSON, and a `Location` header in the form `/getpersons{id}`. This matches the current controller implementation.

## Tests

Run all tests:

```powershell
.\gradlew.bat test
```

`PersonControllerTest` provides unit coverage for all three `PersonController` handlers. It mocks `PersonService`, so these tests do not require a running database.

## Project layout

```text
src/main/java/com/example/
├── controller/     HTTP controllers, including PersonController
├── repository/     JPA entities and repositories
└── service/        Application services
src/main/resources/
├── application.properties
└── templates/HelloWorld.html
src/test/java/      Unit and application tests
```

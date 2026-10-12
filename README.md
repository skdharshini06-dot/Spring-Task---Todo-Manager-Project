# TaskFlow – Todo Manager

A lightweight RESTful web service built using Spring Boot and Spring Data JPA to simplify daily task management.

## Features

- Create new tasks
- Retrieve all tasks or a task by ID
- Update existing tasks
- Delete tasks
- Toggle task completion status
- Filter tasks by completion status and priority
- Automatically generate task IDs and creation timestamps
- Store tasks using the H2 in-memory database

## Technologies Used

- Java 21
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Bean Validation

## Project Structure

```text
my-project/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/taskflow/
│       │   ├── TaskFlowApplication.java
│       │   ├── Task.java
│       │   ├── TaskRepository.java
│       │   └── TaskController.java
│       └── resources/
│           └── application.properties
├── Dockerfile
└── README.md
```

## Prerequisites

- Java 21 or later
- Apache Maven 3.9 or compatible version

## How to Run

1. Open a terminal in the project root directory.
2. Compile the project:

   ```bash
   mvn clean compile
   ```

3. Start the application:

   ```bash
   mvn spring-boot:run
   ```

4. Access the REST API at:

   `http://localhost:8080/api/tasks`

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks` | Retrieve all tasks |
| GET | `/api/tasks/{id}` | Retrieve a task by ID |
| PUT | `/api/tasks/{id}` | Update a task |
| PATCH | `/api/tasks/{id}/toggle` | Toggle completion status |
| DELETE | `/api/tasks/{id}` | Delete a task |

### Filtering

Filter tasks by priority:

`GET /api/tasks?priority=HIGH`

Filter tasks by completion status:

`GET /api/tasks?completed=false`

Both filters can be combined:

`GET /api/tasks?completed=false&priority=HIGH`

## Sample Request

Create a task using a POST request with the following JSON body:

```json
{
  "title": "Complete Spring Boot Project",
  "description": "Build and test the TaskFlow REST API",
  "completed": false,
  "priority": "HIGH"
}
```

## Database

TaskFlow uses an H2 in-memory database. The database schema is managed by Hibernate, and task data is reset when the application stops.

## Success Criteria

- Application starts successfully.
- Tasks can be created and retrieved.
- Tasks can be updated and deleted.
- Completion status can be toggled.
- Priority and completion filters return matching tasks.

## Author

Developed as a Spring Boot Task Management project.
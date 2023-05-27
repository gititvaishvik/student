# Student Management System

The Student Management System is a backend API that provides functionality for managing student details. It includes APIs for loading student details in a paginated manner and implementing server-side filtering based on various columns.

## Technologies Used

- Java
- Spring Boot
- Maven

## Setup

1. Clone the repository:
   `git clone <repository-url>`
2. Build the project using Maven:
`mvn clean install`
3. Run the application:
 `mvn spring-boot:run`


## Endpoints

### Load Student Details API

This API retrieves student details in a paginated manner.

**Endpoint:** `GET /students`

**Parameters:**
- `page` (optional): Page number for pagination (default: 1)
- `size` (optional): Number of records per page (default: 10)

**Response:**
- Status: 200 OK
- Body: JSON object representing the paginated student details

### Server-side Filtering API

This API allows filtering of student details based on various criteria.

**Endpoint:** `GET /filter`

**Parameters:**
- `name` (optional): Filter students by name
- `totalMarks` (optional): Filter students by total marks (greater than or equal to)
- `grade` (optional): Filter students by grade

**Response:**
- Status: 200 OK
- Body: JSON array representing the filtered student details


## Data Source

The student details are stored in a CSV file located at `src/main/resources/studentsdata.csv`. The file should follow the format: id, name, totalMarks, age, grade.


Feel free to explore and extend the Student Management System according to your requirements.

If you have any questions or need assistance, please contact Vaishvik Chaudhari at vaishvik.2020@gmail.com.

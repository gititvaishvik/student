package com.kinaracapitals.student.service;

import com.kinaracapitals.student.entity.Student;
import com.kinaracapitals.student.entity.StudentPage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final String CSV_FILE_PATH = "src/main/resources/studentsdata.csv";

    public StudentPage getStudents(int page, int size) {
        try {
            List<Student> allStudents = readStudentsFromFile();
            List<Student> paginatedStudents = paginateStudents(allStudents, page, size);

            return new StudentPage(paginatedStudents, page, size, allStudents.size());
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            throw new RuntimeException("Error occurred while retrieving students. Please try again later.", e);
        }
    }

//    reads students from file
    private List<Student> readStudentsFromFile() throws IOException {
        Path filePath = Paths.get(CSV_FILE_PATH);
        return Files.lines(filePath)
                .skip(1) // Skip the header row
                .map(this::mapToStudent)
                .collect(Collectors.toList());
    }

    private Student mapToStudent(String line) {
        String[] values = line.split(",");
        // Assuming the order of values in the CSV is: id, name, totalMarks, age, grade
        long id = Long.parseLong(values[0]);
        String name = values[1];
        int totalMarks = Integer.parseInt(values[2]);
        int age = Integer.parseInt(values[3]);
        String grade = values[4];

        return new Student(id, name, totalMarks, age, grade);
    }

    private List<Student> paginateStudents(List<Student> students, int page, int size) {
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, students.size());

        return students.subList(startIndex, endIndex);
    }

    public List<Student> filterStudents(String name, int totalMarks, String grade) throws IOException {
        // Retrieve all students
        List<Student> allStudents = readStudentsFromFile();

        return allStudents.stream()
                .filter(student -> (name == null || student.getName().equals(name)))
                .filter(student -> (totalMarks == -1 || student.getTotalMarks() == totalMarks))
                .filter(student -> (grade == null || student.getGrade().equals(grade)))
                .collect(Collectors.toList());
    }
}


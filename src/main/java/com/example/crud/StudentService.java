package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    // 복수의 StudentDto 를 담는 변수
    private final List<StudentDto> studentList = new ArrayList<>();
    private long nextId = 1L;

    // 새로운 StudentDto 를 생성하는 메소드
    public StudentDto createStudent(String name, String email){
        StudentDto newStudent = new StudentDto(
                nextId, name, email
        );
        nextId++;
        studentList.add(newStudent);
        return newStudent;
    }
    public List<StudentDto> readStudentAll() {
        return studentList;
    }
}

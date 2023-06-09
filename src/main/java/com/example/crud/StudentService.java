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

    public StudentService(){
        createStudent("alex", "alex@gmail.com");
        createStudent("brad", "brad@gmail.com");
        createStudent("chad", "chad@gmail.com");
    }

    // 새로운 StudentDto 를 생성하는 메소드
    public StudentDto createStudent(String name, String email) {
        StudentDto newStudent = new StudentDto(nextId, name, email);
        nextId++;
        studentList.add(newStudent);
        return newStudent;
    }

    // Service 에서 모든 StudentDto 를 주는 메소드
    public List<StudentDto> readStudentAll() {
        return studentList;
    }

    // Service 에서 단일 StudentDto 를 주는 메소드를 만들기
    public StudentDto readStudent(Long id) {
        // TODO
        for (StudentDto studentDto : studentList) {
            if (studentDto.getId().equals(id)){
                return studentDto;
            }
        }
        return null;
    }
    public StudentDto updateStudent(Long id, String name, String email){
        StudentDto targetDto = this.readStudent(id);
        if (targetDto != null){
            targetDto.setName(name);
            targetDto.setEmail(email);
            return targetDto;
        } else return null;
    }

    public boolean deleteStudent(Long id){
        int target = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }
        if (target != -1){
            studentList.remove(target);
            return true;
        } else return false;
    }
}

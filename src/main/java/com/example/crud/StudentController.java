package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    // StudentService 를 controller 에서 사용
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("email")
            String email
    ) {
        System.out.println(name);
        System.out.println(email);
        StudentDto studentDto = studentService.createStudent(name, email);
        System.out.println(studentDto.toString());
        // 데이터를 전송을 하고 새로고침 했을때 post 요청이 다시 중복해서
        // 발생 할 수 있기 때문에 redirect 를 사용해서
        // 데이터 처리가 잘 되었으니 다른 곳(url)으로 이동하게 하기 (Get 으로)
//        return "/create-view";
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(
                "studentList",
                studentService.readStudentAll()
        );
        return "home";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );
        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(
            @PathVariable("id") Long id,
            Model model
    ) {
        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {
        // service 활용하기
        studentService.updateStudent(id, name, email);
        return "redirect:/" + id;
    }

    @GetMapping("/{id}/delete-view")
    public String deleteView(
            @PathVariable("id") Long id,
            Model model
    ) {
        StudentDto studentDto = studentService.readStudent(id);
        model.addAttribute("student", studentDto);
        return "delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable("id") Long id
    ) {
        studentService.deleteStudent(id);
        // update 때는 데이터가 남아있지만
        // delete 는 돌아갈 상세보기가 없다
        // 그래서 홈으로 돌아간다.
        return "redirect:/home";
    }
}

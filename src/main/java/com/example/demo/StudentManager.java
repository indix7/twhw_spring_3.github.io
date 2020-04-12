package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class StudentManager {
    private final StudentRepository studentRepository;
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManager(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.studentRepository.findAll().forEach(student -> students.add(student));
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        if (students.contains(student)){
            return "姓名重复";
        }else {
            students.add(student);
            studentRepository.realSave(student.getName(), student.getGender(), student.getClassmate());
            return "添加成功";
        }
    }

    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        studentRepository.findAll().forEach(studentLinkedList::add);
        return studentLinkedList;
    }

    @PostMapping("/findStudentByName")
    public Student findStudentByName(@RequestBody String name) {
        return studentRepository.findById(name).orElse(null);
    }

    @PostMapping("/deleteStudentByName")
    public String deleteStudentByName(@RequestBody String name) {
        if (students.removeIf(student -> student.getName().equals(name))){
            studentRepository.deleteById(name);
            return "删除成功";
        } else {
            return "删除失败";
        }

    }





}

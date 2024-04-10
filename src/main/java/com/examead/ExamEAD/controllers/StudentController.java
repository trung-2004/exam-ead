package com.examead.ExamEAD.controllers;

import com.examead.ExamEAD.dtos.ResponseObject;
import com.examead.ExamEAD.dtos.StudentDTO;
import com.examead.ExamEAD.dtos.StudentInfomation;
import com.examead.ExamEAD.dtos.StudentScoreDTO;
import com.examead.ExamEAD.models.CreateScoreStudent;
import com.examead.ExamEAD.models.CreateStudent;
import com.examead.ExamEAD.services.IStudentService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private Validator validator;

    @GetMapping("/infomation")
    ResponseEntity<ResponseObject> getAllStudentInfomation() {
        try {
            List<StudentInfomation> list = studentService.getInfomationStudents();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "Success", list)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, e.getMessage(), "")
            );
        }
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> insertStudent(@Valid @RequestBody CreateStudent createStudent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(false, "Validation error", errors)
            );
        }
        try {
            StudentDTO studentDTO = studentService.createStudent(createStudent);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "Success", studentDTO)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/score")
    ResponseEntity<ResponseObject> insertScoreStudent(@Valid @RequestBody CreateScoreStudent createScoreStudent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(false, "Validation error", errors)
            );
        }

        try {
            StudentScoreDTO studentScoreDTO = studentService.insertScore(createScoreStudent);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(true, "Success", studentScoreDTO)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(false, e.getMessage(), "")
            );
        }
    }
}

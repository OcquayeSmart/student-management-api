package com.smartocquaye.studentmanagementapi.controllers;

import com.smartocquaye.studentmanagementapi.dtos.ChangePasswordRequest;
import com.smartocquaye.studentmanagementapi.dtos.CreateStudentRequest;
import com.smartocquaye.studentmanagementapi.dtos.StudentDto;
import com.smartocquaye.studentmanagementapi.dtos.UpdateStudentRequest;
import com.smartocquaye.studentmanagementapi.mappers.StudentMapper;
import com.smartocquaye.studentmanagementapi.repositories.StudentRepository;
import com.smartocquaye.studentmanagementapi.services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("student")
public class StudentController {
    //fredalartey email password = mymommymom
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @GetMapping
    //Use the HTTP status codes instead of the response entity
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @PostMapping
    public ResponseEntity<?> createStudent(
            @Valid @RequestBody CreateStudentRequest createStudentRequest,
            UriComponentsBuilder uriComponentsBuilder){
        return studentService.createStudent(createStudentRequest, uriComponentsBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id, //points to a particular resource
            @RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.updateStudent(updateStudentRequest, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "id") Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/me")
    public ResponseEntity<StudentDto> getOwnStudentRecord(
            Authentication authentication) {

        return studentService.getOwnStudentRecord(authentication.getName());
    }


//    @PostMapping("/{id}/change-password")
//    public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest changePasswordRequest){
//        return studentService.changePassword(id, changePasswordRequest);
//    }
}

package com.smartocquaye.studentmanagementapi.services;

import com.smartocquaye.studentmanagementapi.dtos.ChangePasswordRequest;
import com.smartocquaye.studentmanagementapi.dtos.CreateStudentRequest;
import com.smartocquaye.studentmanagementapi.dtos.StudentDto;
import com.smartocquaye.studentmanagementapi.dtos.UpdateStudentRequest;
import com.smartocquaye.studentmanagementapi.entities.Role;
import com.smartocquaye.studentmanagementapi.entities.Student;
import com.smartocquaye.studentmanagementapi.entities.User;
import com.smartocquaye.studentmanagementapi.mappers.StudentMapper;
import com.smartocquaye.studentmanagementapi.repositories.StudentRepository;
import com.smartocquaye.studentmanagementapi.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Getter
@Setter
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;

    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);
    }

    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        StudentDto studentDto = studentMapper.toDto(student);
        return ResponseEntity.ok(studentDto);
    }

    public ResponseEntity<?> createStudent(
            @Valid @RequestBody CreateStudentRequest createStudentRequest,
            UriComponentsBuilder uriComponentsBuilder) {
        //validation for the email service
        if(studentRepository.existsByEmail(createStudentRequest.getEmail())){
            return ResponseEntity.badRequest().body(Map.of("email", "email is already registered"));
        }

        User user = userRepository.findByEmail(
                createStudentRequest.getEmail().trim().toLowerCase()
        ).orElse(null);

        if(user == null){
            return ResponseEntity.badRequest()
                    .body(Map.of("email", "No registered User account exists for this email"));
        }

        if(user.getRole() != Role.STUDENT){
            return ResponseEntity.badRequest()
                    .body(Map.of("email", "Only users with the STUDENT role can have student profiles"));
        }

        Student student = studentMapper.toEntity(createStudentRequest);
        student.setUser(user);
        user.setStudent(student);
        studentRepository.save(student);
        //convert to dto
        StudentDto studentDto = studentMapper.toDto(student);

        var Uri = uriComponentsBuilder
                .path("/student/{id}")
                .buildAndExpand(studentDto.getId())
                .toUri();

        return ResponseEntity.created(Uri).body(studentDto);
    }

    public ResponseEntity<StudentDto> updateStudent(
            @RequestBody UpdateStudentRequest updateStudentRequest,
            @PathVariable Long id
    ) {
        //first we have to do validation
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        studentMapper.update(updateStudentRequest, student);
        studentRepository.save(student);

        StudentDto studentDto = studentMapper.toDto(student);
        return ResponseEntity.ok().body(studentDto);
    }

    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.delete(student);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<StudentDto> getOwnStudentRecord(String email) {
        Student student = studentRepository.findByUser_Email(email).orElse(null);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentMapper.toDto(student));
    }

//    public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest changePasswordRequest){
//        Student student = studentRepository.findById(id).orElse(null);
//        if(student == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        if(!student.getPassword().equals(changePasswordRequest.getOldPassword())){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        student.setPassword(changePasswordRequest.getNewPassword());
//        studentRepository.save(student);
//        return ResponseEntity.noContent().build();
//    }
}

package com.smartocquaye.studentmanagementapi.repositories;

import com.smartocquaye.studentmanagementapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    boolean existsByUser_id(Long userId);
    Optional<Student> findByUser_Email(String email);
}

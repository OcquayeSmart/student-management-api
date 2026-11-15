package com.smartocquaye.studentmanagementapi.mappers;

import com.smartocquaye.studentmanagementapi.dtos.CreateStudentRequest;
import com.smartocquaye.studentmanagementapi.dtos.StudentDto;
import com.smartocquaye.studentmanagementapi.dtos.UpdateStudentRequest;
import com.smartocquaye.studentmanagementapi.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    //for any problem with the mapper, rebuild project
    StudentDto toDto(Student student);
    Student toEntity(StudentDto studentDto);
    Student toEntity(CreateStudentRequest createStudentRequest);
    void update(UpdateStudentRequest updateStudentRequest, @MappingTarget Student student);
}
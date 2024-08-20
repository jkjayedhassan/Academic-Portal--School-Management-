package com.idb.crud.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Department;
import com.idb.crud.entity.Student;
import com.idb.crud.entity.StudyClass;
import com.idb.crud.service.StudentService;
import com.idb.crud.utils.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController implements BaseController<Student, Long> {

    private final StudentService studentService;

    @Override
    public ResponseEntity<Response<?>> save(Student data) {
        if (data.hasId()) {
            return ResponseEntity.ok(studentService.merge(data));
        } else {
            return ResponseEntity.ok(studentService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "Student deletion not allowed", null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Student>> findById(Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Student>>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    public ResponseEntity<Response<Page<Student>>> findAllByPage(
            Integer pageNumber, Integer pageSize,
            String sortColumn, String sortOrder, String searchKey) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, sortOrder);
        return ResponseEntity.ok(studentService.findAll(pageable, searchKey));
    }

    @GetMapping("/find_students/{classId}/{departmentId}")
    public ResponseEntity<Response<List<Student>>> findByStClassAndDept(
            @PathVariable("classId") Long classId, 
            @PathVariable("departmentId") Long departmentId) {
        
                StudyClass studyClass = new StudyClass();
                studyClass.setId(classId);
                Department department = new Department();
                department.setId(departmentId);
        List<Student> students = studentService.findByStClassAndDept(studyClass, department);
        return ResponseEntity.ok(new Response<>(ResponseStatus.SUCCESS, "Students fetched successfully", students));
    }
}

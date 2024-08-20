package com.idb.crud.service;

import java.util.List;

import com.idb.crud.entity.Department;
import com.idb.crud.entity.Student;
import com.idb.crud.entity.StudyClass;

public interface StudentService extends BaseService<Student, Long> {
    List<Student> findByStClassAndDept(StudyClass studyClass, Department department);
    
}

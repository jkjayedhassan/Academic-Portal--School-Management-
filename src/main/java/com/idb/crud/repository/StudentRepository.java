package com.idb.crud.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idb.crud.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  boolean existsByRoll(String roll);

  @Query("select s from Student s where s.firstName like %?1% or s.lastName like %?1% or s.roll like %?1%  or s.session like %?1% or s.stClass.className like %?1%")
  Page<Student> search(String keyword, Pageable pageable);

  @Query("select s from Student s where s.stClass.id = ?1 and s.dept.id = ?2")
  List<Student> findByStClassIdAndDeptId(Long studyClass, Long department);

  
}

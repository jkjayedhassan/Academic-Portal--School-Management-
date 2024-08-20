package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idb.crud.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{
   
  
}

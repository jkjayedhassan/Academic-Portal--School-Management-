package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idb.crud.entity.StudyClass;
@Repository
public interface StudyClassRepository extends JpaRepository<StudyClass, Long> {
    boolean existsByClassName(String className);  
} 
package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idb.crud.entity.ExamResult;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
}

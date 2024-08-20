package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idb.crud.entity.SubjectResult;

public interface SubjectResultRepository extends JpaRepository<SubjectResult, Long> {
    // Additional custom query methods can be added here if needed
}

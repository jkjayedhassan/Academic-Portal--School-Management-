package com.idb.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idb.crud.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);

    Optional<Department> findByName(String name);

    @Query("SELECT d FROM Department d JOIN d.classes c WHERE c.id = :classId")
    List<Department> findDepartmentsByClassId(@Param("classId") Long classId);

}

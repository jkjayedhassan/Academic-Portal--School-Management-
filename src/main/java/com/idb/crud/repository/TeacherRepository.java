package com.idb.crud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idb.crud.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmployeeId(String employeeId);

    @Query("select t from Teacher t where t.fullName like %?1% or t.contactNumber like %?1% or t.department like %?1% or t.employeeId like %?1% or t.academicSession like %?1%")
    Page<Teacher> search(String keyword, Pageable pageable);
}

package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idb.crud.entity.AttendanceRecord;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
   // Optional<AttendanceRecord> findByRoll(String roll);
}

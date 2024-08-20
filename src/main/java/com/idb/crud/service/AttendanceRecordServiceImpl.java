package com.idb.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.AttendanceRecord;
import com.idb.crud.repository.AttendanceRecordRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    private final AttendanceRecordRepository attendanceRecordRepository;

    @Override
    public Response<?> persist(AttendanceRecord attendanceRecord) {
        if (attendanceRecord.hasId()) {
            attendanceRecord.setId(null);
        }

        // Check for existing record with the same roll
        // Optional<AttendanceRecord> existingRecord = attendanceRecordRepository.findByRoll(attendanceRecord.getRoll());
        // if (existingRecord.isPresent()) {
        //     return new Response<>(ResponseStatus.ERROR, "Duplicate entry for roll: " + attendanceRecord.getRoll());
        // }

        attendanceRecordRepository.save(attendanceRecord);
        return new Response<>(ResponseStatus.SUCCESS, "Student attendance saved successfully");
    }

    @Override
    public Response<?> merge(AttendanceRecord attendanceRecord) {
        if (!attendanceRecord.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }

        AttendanceRecord oldAttendanceRecord = attendanceRecordRepository.findById(attendanceRecord.getId()).orElse(null);
        if (oldAttendanceRecord == null) {
            return new Response<>(ResponseStatus.ERROR, "Student attendance not found");
        }

        attendanceRecordRepository.save(attendanceRecord);
        return new Response<>(ResponseStatus.SUCCESS, "Student attendance updated successfully");
    }

    @Override
    public Response<List<AttendanceRecord>> findAll() {
        List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, attendanceRecords);
    }

    @Override
    public Response<AttendanceRecord> findById(Long id) {
        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if (attendanceRecord.isPresent()) {
            return new Response<>(ResponseStatus.SUCCESS, null, attendanceRecord.get());
        } else {
            return new Response<>(ResponseStatus.ERROR, "Attendance record not found");
        }
    }

    @Override
    public Response<?> deleteById(Long id) {
        if (!attendanceRecordRepository.existsById(id)) {
            return new Response<>(ResponseStatus.ERROR, "Attendance record not found");
        }
        attendanceRecordRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Student attendance deleted successfully");
    }

    @Override
    public Response<?> classAttendance(List<AttendanceRecord> entity) {
        LocalDateTime date = LocalDateTime.now();
        for (AttendanceRecord attendanceRecord : entity) {
            attendanceRecord.setDate(date);
        }
        attendanceRecordRepository.saveAll(entity);
        return new Response<>(ResponseStatus.SUCCESS, "Student attendance saved successfully");
    }
}

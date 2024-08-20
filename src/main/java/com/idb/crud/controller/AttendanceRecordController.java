package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.AttendanceRecord;
import com.idb.crud.service.AttendanceRecordService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceRecordController implements BaseController<AttendanceRecord, Long> {

    private final AttendanceRecordService attendanceRecordService;

    @Override
    public ResponseEntity<Response<?>> save(AttendanceRecord data) {
        if (data.hasId()) {
            return ResponseEntity.ok(attendanceRecordService.merge(data));
        } else {
            return ResponseEntity.ok(attendanceRecordService.persist(data));
        }
    }

    @PostMapping("/class-attendance")
    public ResponseEntity<Response<?>> save(@RequestBody List<AttendanceRecord> data) {
        if (data != null && !data.isEmpty()) {
            return ResponseEntity.ok(attendanceRecordService.classAttendance(data));
        } else {
            return ResponseEntity.ok(new Response<>(ResponseStatus.ERROR, "AttendanceRecord not found", null));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "AttendanceRecord deletion not allowed", null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<AttendanceRecord>> findById(Long id) {
        return ResponseEntity.ok(attendanceRecordService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<AttendanceRecord>>> findAll() {

        return ResponseEntity.ok(attendanceRecordService.findAll());
    }

}

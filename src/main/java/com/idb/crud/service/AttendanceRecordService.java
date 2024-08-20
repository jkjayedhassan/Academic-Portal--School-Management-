package com.idb.crud.service;

import java.util.List;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.AttendanceRecord;

public interface AttendanceRecordService extends BaseService<AttendanceRecord, Long> {

    Response<?> classAttendance(List<AttendanceRecord> entity);
}

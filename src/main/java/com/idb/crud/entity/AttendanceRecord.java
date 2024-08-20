package com.idb.crud.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attendance_record")
public class AttendanceRecord extends BaseEntity {

    @Column(name = "att_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "class_id", nullable = false)
    private long classId;

    @Column(name = "dept_id", nullable = false)
    private long departmentId;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "is_present", nullable = false)
    private boolean isPresent;

}

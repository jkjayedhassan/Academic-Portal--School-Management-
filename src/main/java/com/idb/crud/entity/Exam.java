package com.idb.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exam")
public class Exam extends BaseEntity {
    @Column(name = "st_class", nullable = false)
    private String stClass;
    @Column(name = "subject_Name", nullable = false, columnDefinition = "varchar(255)")
    private String subject;
    @Column(name = "exam_Date", nullable = false, columnDefinition = "varchar(255)")
    private Date examDate;
    @Column(name = "exam_Details", nullable = false, columnDefinition = "varchar(255)")
    private String examDetails;
    
}

package com.idb.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject_result")
public class SubjectResult extends BaseEntity {

    @Column(name = "subject_name", nullable = false, columnDefinition = "varchar(255)")
    private String subjectName;

    @Column(name = "marks", nullable = false)
    private int marks;

    @Column(name = "grade", nullable = false, columnDefinition = "varchar(10)")
    private String grade;
}

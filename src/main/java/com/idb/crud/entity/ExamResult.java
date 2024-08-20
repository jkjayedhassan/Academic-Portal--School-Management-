package com.idb.crud.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exam_result")
public class ExamResult extends BaseEntity {

    @Column(name = "student_name", nullable = false, columnDefinition = "varchar(255)")
    private String studentName;

    @Column(name = "roll", nullable = false, columnDefinition = "varchar(255)")
    private String roll;

    @Column(name = "class_name", columnDefinition = "varchar(255)")
    private String className;

    @Column(name = "department_name", columnDefinition = "varchar(255)")
    private String departmentName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "exam_result_subjects",
        joinColumns = @JoinColumn(name = "exam_result_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_result_id")
    )
    private Set<SubjectResult> subjectResults = new HashSet<>();
}

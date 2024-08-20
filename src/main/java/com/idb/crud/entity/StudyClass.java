package com.idb.crud.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "study_class")
public class StudyClass extends BaseEntity {
    @Column(name = "class_name", nullable = true, unique = true, columnDefinition = "varchar(255)")
    private String className;

    @Column(name = "class_teacher", columnDefinition = "varchar(255)")
    private String classTeacher;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "department_classes", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments = new HashSet<>();

    @ManyToMany(mappedBy = "studyClass", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JsonIgnore
    private Set<Subject> subjects = new HashSet<>();

  
    
}

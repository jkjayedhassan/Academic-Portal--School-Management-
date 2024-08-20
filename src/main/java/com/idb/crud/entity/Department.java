package com.idb.crud.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department extends BaseEntity {
    @Column(name = "department_name", unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Teacher> teachers;

    // @JsonIgnore
    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    // @JoinTable(name = "department_classes", joinColumns = @JoinColumn(name =
    // "department_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
    // private List<StudyClass> classes;

    @JsonIgnore
    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    private Set<StudyClass> classes = new HashSet<>();

}

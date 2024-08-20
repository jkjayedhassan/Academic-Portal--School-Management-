package com.idb.crud.entity;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(255)")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(255)")
    private String lastName;
    @Column(name = "roll", nullable = false)
    private String roll;

    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "st_class")
    private StudyClass stClass;
    
    @Column(name = "session")
    private String session;
    @Lob
    @Column(name = "photo", nullable = true, columnDefinition = "LONGBLOB")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "student_subjects", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id")
    private Department dept;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    public Student(){};
   public Student(String firstName, String lastName, String roll, String address, StudyClass stClass, String session, String photo) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.roll = roll;
    this.address = address;
    this.stClass = stClass;
    this.session = session;
    this.photo = photo;
}
}


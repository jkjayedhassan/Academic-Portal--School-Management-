package com.idb.crud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher extends BaseEntity {
    @Lob
    @Column(name = "photo", nullable = true, columnDefinition = "LONGBLOB")
    private String photo;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "department")
    private String department;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "academic_session")
    private String academicSession;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department dept;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}

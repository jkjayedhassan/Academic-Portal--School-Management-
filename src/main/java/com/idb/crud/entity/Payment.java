package com.idb.crud.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    @Column(name = "st_class", nullable = false)
    private String stClassName;

    @Column(name = "student_name", nullable = false)
    private String studentName;
    
    @Column(name = "roll", nullable = false)
    private String roll;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "payment_reason", nullable = false)
    private String paymentReason;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
}

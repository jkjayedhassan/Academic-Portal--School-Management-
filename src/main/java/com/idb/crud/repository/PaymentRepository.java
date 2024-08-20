package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idb.crud.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
   
}

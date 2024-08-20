package com.idb.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Payment;
import com.idb.crud.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Response<?> persist(Payment payment) {
        if (payment.hasId()) {
            payment.setId(null);
        }
       

        paymentRepository.save(payment);
        return new Response<>(ResponseStatus.SUCCESS, "Payment saved successfully");
    }

    @Override
    public Response<?> merge(Payment payment) {     
        if (!payment.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required"); 
        }
        Payment oldPayment = paymentRepository.findById(payment.getId()).orElse(null);
        if (oldPayment == null) {
            return new Response<>(ResponseStatus.ERROR, "Payment not found");
        }
    
        paymentRepository.save(payment);
        return new Response<>(ResponseStatus.SUCCESS, "Payment updated successfully");
    }

    @Override
    public Response<List<Payment>> findAll() {  
        List<Payment> payments = paymentRepository.findAll();       
        return new Response<>(ResponseStatus.SUCCESS, null, payments);
    }

    @Override
    public Response<Payment> findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, payment);
    }

    @Override
    public Response<?> deleteById(Long id) {
        paymentRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Payment deleted successfully");
    }
    
}

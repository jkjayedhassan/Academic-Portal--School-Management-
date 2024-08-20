package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Payment;
import com.idb.crud.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController implements BaseController<Payment, Long> {
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<Response<?>> save(Payment data) {
        
        if(data.hasId()) {
            return ResponseEntity.ok(paymentService.merge(data));
        } else {
            return ResponseEntity.ok(paymentService.persist(data));
        }    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
         Response<?> response = new Response<>(ResponseStatus.ERROR, "Deletion Complete", null);
        ResponseEntity.ok(paymentService.deleteById(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Payment>> findById(Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
 
    }

    @Override
    public ResponseEntity<Response<List<Payment>>> findAll() {
        return ResponseEntity.ok(paymentService.findAll());

    }

 
}

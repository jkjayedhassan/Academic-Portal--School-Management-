package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.ExamResult;
import com.idb.crud.service.ExamResultService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exam-result")
@CrossOrigin(origins = "*")
public class ExamResultController implements BaseController<ExamResult, Long> {
    private final ExamResultService examResultService;

    @Override
    public ResponseEntity<Response<?>> save(ExamResult data) {
        if (data.hasId()) {
            return ResponseEntity.ok(examResultService.merge(data));
        } else {
            return ResponseEntity.ok(examResultService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "Deletion Complete", null);
        ResponseEntity.ok(examResultService.deleteById(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<ExamResult>> findById(Long id) {
        return ResponseEntity.ok(examResultService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<ExamResult>>> findAll() {
        return ResponseEntity.ok(examResultService.findAll());
    }
}

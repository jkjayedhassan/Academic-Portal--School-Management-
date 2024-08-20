package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Exam;
import com.idb.crud.service.ExamService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "*")
public class ExamController implements BaseController<Exam, Long> {
    private final ExamService examService;

    @Override
    public ResponseEntity<Response<?>> save(Exam data) {

        if (data.hasId()) {
            return ResponseEntity.ok(examService.merge(data));
        } else {
            return ResponseEntity.ok(examService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "Deletion Complete", null);
        ResponseEntity.ok(examService.deleteById(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Exam>> findById(Long id) {
        return ResponseEntity.ok(examService.findById(id));

    }

    @Override
    public ResponseEntity<Response<List<Exam>>> findAll() {
        return ResponseEntity.ok(examService.findAll());

    }

}

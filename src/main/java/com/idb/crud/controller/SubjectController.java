package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Subject;
import com.idb.crud.service.SubjectService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*")


public class SubjectController implements BaseController<Subject, Long> {
    private final SubjectService subjectService;
    @Override
    public ResponseEntity<Response<?>> save(Subject data) {
        if (data.hasId()) {
            return ResponseEntity.ok(subjectService.merge(data));
        } else {
            return ResponseEntity.ok(subjectService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "Subject deletion not allowed", null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Subject>> findById(Long id) {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Subject>>> findAll() {

        return ResponseEntity.ok(subjectService.findAll());
    }

}

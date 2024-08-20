package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.SubjectResult;
import com.idb.crud.service.SubjectResultService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subject-result")
@CrossOrigin(origins = "*")
public class SubjectResultController implements BaseController<SubjectResult, Long> {
    private final SubjectResultService subjectResultService;

    @Override
    public ResponseEntity<Response<?>> save(SubjectResult data) {
        if (data.hasId()) {
            return ResponseEntity.ok(subjectResultService.merge(data));
        } else {
            return ResponseEntity.ok(subjectResultService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "Deletion Complete", null);
        ResponseEntity.ok(subjectResultService.deleteById(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<SubjectResult>> findById(Long id) {
        return ResponseEntity.ok(subjectResultService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<SubjectResult>>> findAll() {
        return ResponseEntity.ok(subjectResultService.findAll());
    }
}

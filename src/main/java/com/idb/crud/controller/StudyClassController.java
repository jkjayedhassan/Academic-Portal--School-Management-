package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.StudyClass;
import com.idb.crud.service.StudyClassService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping( "/studyclass")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class StudyClassController implements BaseController<StudyClass, Long>{
private final StudyClassService studyClassService;

@Override
    public ResponseEntity<Response<?>> save(StudyClass data) {
        if(data.hasId()) {
            return ResponseEntity.ok(studyClassService.merge(data));
        } else {
            return ResponseEntity.ok(studyClassService.persist(data));
        }
    }
    
    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(studyClassService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<StudyClass>> findById(Long id) {
        return ResponseEntity.ok(studyClassService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<StudyClass>>> findAll() {
        return ResponseEntity.ok(studyClassService.findAll());
    }
}

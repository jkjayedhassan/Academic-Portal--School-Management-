package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.Department;
import com.idb.crud.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})

public class DepartmentController implements BaseController<Department, Long>{
    private final DepartmentService departmentService;

    @Override
    public ResponseEntity<Response<?>> save(Department data) {
        if(data.hasId()) {
            return ResponseEntity.ok(departmentService.merge(data));
        } else {
            return ResponseEntity.ok(departmentService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(departmentService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<Department>> findById(Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Department>>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

}


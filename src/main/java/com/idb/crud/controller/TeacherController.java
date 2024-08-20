package com.idb.crud.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Teacher;
import com.idb.crud.service.TeacherService;
import com.idb.crud.utils.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teacher")
public class TeacherController implements BaseController<Teacher, Long> {
    private final TeacherService teacherService;

       @Override
    public ResponseEntity<Response<?>> save(Teacher data) {
        if(data.hasId()) {
            return ResponseEntity.ok(teacherService.merge(data));
        } else {
            return ResponseEntity.ok(teacherService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        Response<?> response = new Response<>(ResponseStatus.ERROR, "Teacher deletion not allowed", null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Teacher>> findById(Long id) {
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Teacher>>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

  @Override
    public ResponseEntity<Response<Page<Teacher>>> findAllByPage(
        Integer pageNumber, Integer pageSize, 
        String sortColumn, String sortOrder, String searchKey
    ) {
        Pageable pageable = PageUtil.getPageable(pageNumber, pageSize, sortColumn, sortOrder);
        return ResponseEntity.ok(teacherService.findAll(pageable, searchKey));
    }
}

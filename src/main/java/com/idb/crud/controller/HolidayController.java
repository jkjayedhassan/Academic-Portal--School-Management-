package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.Holiday;
import com.idb.crud.service.HolidayService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/holiday")
@CrossOrigin(origins = "*")
public class HolidayController implements BaseController<Holiday, Long> {
    private final HolidayService holidayService;

    @Override
    public ResponseEntity<Response<?>> save(Holiday data) {
        return ResponseEntity.ok(holidayService.persist(data));
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(holidayService.deleteById(id));
    }

    @Override

    public ResponseEntity<Response<Holiday>> findById(Long id) {
        return ResponseEntity.ok(holidayService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<Holiday>>> findAll() {
        return ResponseEntity.ok(holidayService.findAll());
    }

}

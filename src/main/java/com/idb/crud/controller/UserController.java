package com.idb.crud.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.User;
import com.idb.crud.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController implements BaseController<User, Long> {

    private final UserService userService;

    @Override
    public ResponseEntity<Response<?>> save(User data) {
        if (data.hasId()) {
            return ResponseEntity.ok(userService.merge(data));
        } else {
            return ResponseEntity.ok(userService.persist(data));
        }
    }

    @Override
    public ResponseEntity<Response<?>> deleteById(Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @Override
    public ResponseEntity<Response<User>> findById(Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<Response<List<User>>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
    @PostMapping("/login")
    public ResponseEntity<Response<?>> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }
    
}

package com.idb.crud.service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.User;
import com.idb.crud.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    // @Value("${spring.application.name:myapp}")
    // private String appName;

    @Override
    public Response<?> persist(User user) {
        boolean usernameExists = userRepository.existsByUsername(user.getUsername());
        if (usernameExists) {
            return new Response<>(ResponseStatus.ERROR, "Username already exists");
        }
        boolean emailExists = userRepository.existsByEmail(user.getEmail());
        if (emailExists) {
            return new Response<>(ResponseStatus.ERROR, "Email already exists");
        }
        userRepository.save(user);
        return new Response<>(ResponseStatus.SUCCESS, "User saved successfully");
    }

    @Override
    public Response<?> merge(User user) {
        if (!user.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        User olUser = userRepository.findById(user.getId()).orElse(null);
        if (olUser == null) {
            return new Response<>(ResponseStatus.ERROR, "User not found");
        }
        if (!olUser.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(user.getEmail())) {
                return new Response<>(ResponseStatus.ERROR, "User email already exists");
            }
        }

        userRepository.save(user);
        return new Response<>(ResponseStatus.SUCCESS, "User updated successfully");
    }

    @Override
    public Response<List<User>> findAll() {
        List<User> users=userRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, users);
    }

    @Override
    public Response<?> deleteById(Long id) {
       // userRepository.deleteById(id);
        return  new Response<>(ResponseStatus.ERROR, "User deletion not allowed");
    }

    @Override
    public Response<User> findById(Long id) {
        User user=userRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, user);
    }
    @Override
    public Response<String> login(User user) {
        User olUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (olUser == null) {
            return new Response<>(ResponseStatus.ERROR, "Invalid username or password");
        }
        return new Response<>(ResponseStatus.SUCCESS, "Login successful", olUser.getUsername());
    }
}

package com.idb.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Teacher;
import com.idb.crud.repository.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserService userService;

 

    @Override
    public Response<?> persist(Teacher teacher) {
        if (teacher.hasId()) {
            teacher.setId(null);
        }
        if (teacherRepository.existsByEmployeeId(teacher.getEmployeeId())) {
            return new Response<>(ResponseStatus.ERROR, "Teacher ID already exists");
        }
        Response<?> response = userService.persist(teacher.getUser());
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            teacherRepository.save(teacher);
            return new Response<>(ResponseStatus.SUCCESS, "Teacher saved successfully");
        } else {
            return response;
        }
    }

    @Override
    public Response<?> merge(Teacher teacher) {
        if (!teacher.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Teacher olTeacher = teacherRepository.findById(teacher.getId()).orElse(null);
        if (olTeacher == null) {
            return new Response<>(ResponseStatus.ERROR, "Teacher not found");
        }
        if (!olTeacher.getEmployeeId().equals(teacher.getEmployeeId())) {
            if (teacherRepository.existsByEmployeeId(teacher.getEmployeeId())) {
                return new Response<>(ResponseStatus.ERROR, "Teacher ID already exists");
            }
        }
        Response<?> response = userService.merge(teacher.getUser());
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {

            teacherRepository.save(teacher);
            return new Response<>(ResponseStatus.SUCCESS, "Teacher updated successfully");
        } else {
            return response;
        }
    }

    @Override
    public Response<List<Teacher>> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, teachers);
    }

    @Override
    public Response<?> deleteById(Long id) {
        teacherRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Teacher deleted successfully");
    }

    @Override
    public Response<Teacher> findById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, teacher);
    }

      @Override
    public Response<Page<Teacher>> findAll(Pageable pageable, String searchKey) {
        Page<Teacher> teachers;
        if (searchKey == null || searchKey.equals("*")) {
            teachers = teacherRepository.findAll(pageable);
        } else {
            teachers = teacherRepository.search(searchKey, pageable);
        }
        return new Response<>(ResponseStatus.SUCCESS, null, teachers);
    }
}

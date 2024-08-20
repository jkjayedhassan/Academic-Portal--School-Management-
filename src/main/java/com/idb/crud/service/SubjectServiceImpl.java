package com.idb.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Subject;
import com.idb.crud.repository.SubjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    @Override
    public Response<?> persist(Subject subject) {
        if (subject.hasId()) {
            subject.setId(null);
        }
        if (subjectRepository.existsByName(subject.getName())) {
            return new Response<>(ResponseStatus.ERROR, "Subject already exists");
        }
        if (subjectRepository.existsByCode(subject.getCode())) {
            return new Response<>(ResponseStatus.ERROR, "Subject  Code already exists");
        }
        

        subjectRepository.save(subject);
        return new Response<>(ResponseStatus.SUCCESS, "Subject saved successfully");
    }

    @Override
    public Response<?> merge(Subject subject) {
        if (!subject.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Subject oldSubject = subjectRepository.findById(subject.getId()).orElse(null);
        if (oldSubject == null) {
            return new Response<>(ResponseStatus.ERROR, "Subject not found");
        }
        if (!oldSubject.getName().equals(subject.getName())) {
            if (subjectRepository.existsByName(subject.getName())) {
                return new Response<>(ResponseStatus.ERROR, "Subject name already exists");
            }
        }


        subjectRepository.save(subject);
        return new Response<>(ResponseStatus.SUCCESS, "Subject updated successfully");
    }

    @Override
    public Response<List<Subject>> findAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, subjects);
    }

    @Override
    public Response<?> deleteById(Long id) {
        subjectRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Subject deleted successfully");
    }

    @Override
    public Response<Subject> findById(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, subject);
    }
}
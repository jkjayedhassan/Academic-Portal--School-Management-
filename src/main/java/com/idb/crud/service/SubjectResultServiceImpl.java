package com.idb.crud.service;import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.SubjectResult;
import com.idb.crud.repository.SubjectResultRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectResultServiceImpl implements SubjectResultService {

    private final SubjectResultRepository subjectResultRepository;

    @Override
    public Response<?> persist(SubjectResult subjectResult) {
        if (subjectResult.hasId()) {
            subjectResult.setId(null);
        }
        subjectResultRepository.save(subjectResult);
        return new Response<>(ResponseStatus.SUCCESS, "Subject result saved successfully");
    }

    @Override
    public Response<?> merge(SubjectResult subjectResult) {
        if (!subjectResult.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        SubjectResult oldSubjectResult = subjectResultRepository.findById(subjectResult.getId()).orElse(null);
        if (oldSubjectResult == null) {
            return new Response<>(ResponseStatus.ERROR, "Subject result not found");
        }
        subjectResultRepository.save(subjectResult);
        return new Response<>(ResponseStatus.SUCCESS, "Subject result updated successfully");
    }

    @Override
    @Transactional
    public Response<List<SubjectResult>> findAll() {
        List<SubjectResult> subjectResults = subjectResultRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, subjectResults);
    }

    @Override
    public Response<?> deleteById(Long id) {
        subjectResultRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Subject result deleted successfully");
    }

    @Override
    public Response<SubjectResult> findById(Long id) {
        SubjectResult subjectResult = subjectResultRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, subjectResult);
    }
}
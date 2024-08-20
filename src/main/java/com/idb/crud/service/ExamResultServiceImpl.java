package com.idb.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.ExamResult;
import com.idb.crud.repository.ExamResultRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;

    @Override
    public Response<?> persist(ExamResult examResult) {
        if (examResult.hasId()) {
            examResult.setId(null);
        }
        examResultRepository.save(examResult);
        return new Response<>(ResponseStatus.SUCCESS, "Exam result saved successfully");
    }

    @Override
    public Response<?> merge(ExamResult examResult) {
        if (!examResult.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        ExamResult oldExamResult = examResultRepository.findById(examResult.getId()).orElse(null);
        if (oldExamResult == null) {
            return new Response<>(ResponseStatus.ERROR, "Exam result not found");
        }
        examResultRepository.save(examResult);
        return new Response<>(ResponseStatus.SUCCESS, "Exam result updated successfully");
    }

    @Override
    @Transactional
    public Response<List<ExamResult>> findAll() {
        List<ExamResult> examResults = examResultRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, examResults);
    }

    @Override
    public Response<?> deleteById(Long id) {
        examResultRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Exam result deleted successfully");
    }

    @Override
    public Response<ExamResult> findById(Long id) {
        ExamResult examResult = examResultRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, examResult);
    }
}

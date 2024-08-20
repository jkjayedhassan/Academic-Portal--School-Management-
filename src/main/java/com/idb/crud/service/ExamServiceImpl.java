package com.idb.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Exam;
import com.idb.crud.repository.ExamRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    
    @Override
    public Response<?> persist(Exam exam) {
        if (exam.hasId()) {
            exam.setId(null);
        }
      
        examRepository.save(exam);
        return new Response<>(ResponseStatus.SUCCESS, "Exam saved successfully");
    }

    @Override
    public Response<?> merge(Exam exam) {
        if (!exam.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Exam olExam = examRepository.findById(exam.getId()).orElse(null);
        if (olExam == null) {
            return new Response<>(ResponseStatus.ERROR, "Exam not found");
        }
  
        examRepository.save(exam);
        return new Response<>(ResponseStatus.SUCCESS, "Exam updated successfully");
    }

    @Override
    @Transactional
    public Response<List<Exam>> findAll() {
        List<Exam> exams = examRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, exams);
    }

    @Override
    public Response<?> deleteById(Long id) {
        examRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Exam deleted successfully");
    }

    @Override
    public Response<Exam> findById(Long id) {
        Exam exam = examRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, exam);
    }



}

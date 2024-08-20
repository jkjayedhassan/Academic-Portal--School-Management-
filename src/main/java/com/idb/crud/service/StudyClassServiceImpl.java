package com.idb.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.StudyClass;
import com.idb.crud.entity.Subject;
import com.idb.crud.repository.StudyClassRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyClassServiceImpl implements StudyClassService {
    private final StudyClassRepository studyClassRepository;


    @Override
    public Response<?> persist(StudyClass studyClass) {
        if (studyClass.hasId()) {
            studyClass.setId(null);
        }
        if (studyClassRepository.existsByClassName(studyClass.getClassName())) {
            return new Response<>(ResponseStatus.ERROR, "StudyClass name already exists");
        }

    

      

        studyClassRepository.save(studyClass);
        return new Response<>(ResponseStatus.SUCCESS, "StudyClass saved successfully");
    }

    @Override
    public Response<?> merge(StudyClass studyClass) {
        if (!studyClass.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        StudyClass olStudyClass = studyClassRepository.findById(studyClass.getId()).orElse(null);
        if (olStudyClass == null) {
            return new Response<>(ResponseStatus.ERROR, "StudyClass not found");
        }
        if (!olStudyClass.getClassName().equals(studyClass.getClassName())) {
            if (studyClassRepository.existsByClassName(studyClass.getClassName())) {
                return new Response<>(ResponseStatus.ERROR, "StudyClass name already exists");
            }
        }

        // Add study class to subjects
        for (Subject subject : studyClass.getSubjects()) {
            subject.getStudyClass().add(studyClass);
        }

        studyClassRepository.save(studyClass);
        return new Response<>(ResponseStatus.SUCCESS, "StudyClass updated successfully");
    }

    @Override
    @Transactional
    public Response<List<StudyClass>> findAll() {
        List<StudyClass> studyClasses = studyClassRepository.findAll();
        studyClasses.forEach(s -> {
            s.setSubjects(null);
        });
        return new Response<>(ResponseStatus.SUCCESS, null, studyClasses);
    }

    @Override
    public Response<?> deleteById(Long id) {
        studyClassRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "StudyClass deleted successfully");
    }

    @Override
    public Response<StudyClass> findById(Long id) {
        StudyClass studyClass = studyClassRepository.findById(id).orElse(null);
        studyClass.setSubjects(null);
        return new Response<>(ResponseStatus.SUCCESS, null, studyClass);
    }
}

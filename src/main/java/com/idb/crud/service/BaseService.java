package com.idb.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.idb.crud.dto.Response;

public interface BaseService<ENTITY, ID> {
    Response<?> persist(ENTITY entity);

    Response<?> merge(ENTITY entity);

    Response<List<ENTITY>> findAll();

    Response<ENTITY> findById(ID id);

    Response<?> deleteById(ID id);

    default Response<Page<ENTITY>> findAll(Pageable pageable, String searchKey) {
        return null;
    }
}

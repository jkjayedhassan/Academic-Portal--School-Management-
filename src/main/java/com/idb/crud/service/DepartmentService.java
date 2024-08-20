package com.idb.crud.service;

import com.idb.crud.dto.Response;
import com.idb.crud.entity.Department;

public interface DepartmentService extends BaseService<Department, Long> {

    Response<Department> findByName(String string);
    // Response<Department> findDepartmentsByClassId(Long classId);
}

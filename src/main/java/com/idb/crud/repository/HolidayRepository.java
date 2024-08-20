package com.idb.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idb.crud.entity.Holiday;

public interface HolidayRepository extends JpaRepository <Holiday, Long> {

}

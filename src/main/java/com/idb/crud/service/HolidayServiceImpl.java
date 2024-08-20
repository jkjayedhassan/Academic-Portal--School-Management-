package com.idb.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idb.crud.contstants.ResponseStatus;
import com.idb.crud.dto.Response;
import com.idb.crud.entity.Holiday;
import com.idb.crud.repository.HolidayRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;

      @Override
    public Response<?> persist(Holiday holiday) {
        if (holiday.hasId()) {
            holiday.setId(null);
        }
      
        holidayRepository.save(holiday);
        return new Response<>(ResponseStatus.SUCCESS, "holiday saved successfully");
    }

    @Override
    public Response<?> merge(Holiday holiday) {
        if (!holiday.hasId()) {
            return new Response<>(ResponseStatus.ERROR, "ID required");
        }
        Holiday olHoliday = holidayRepository.findById(holiday.getId()).orElse(null);
        if (olHoliday == null) {
            return new Response<>(ResponseStatus.ERROR, "Holiday not found");
        }
  
        holidayRepository.save(holiday);
        return new Response<>(ResponseStatus.SUCCESS, "Holiday updated successfully");
    }

    @Override
    @Transactional
    public Response<List<Holiday>> findAll() {
        List<Holiday> holidays = holidayRepository.findAll();
        return new Response<>(ResponseStatus.SUCCESS, null, holidays);
    }

    @Override
    public Response<?> deleteById(Long id) {
        holidayRepository.deleteById(id);
        return new Response<>(ResponseStatus.SUCCESS, "Holiday deleted successfully");
    }

    @Override
    public Response<Holiday> findById(Long id) {
        Holiday holiday = holidayRepository.findById(id).orElse(null);
        return new Response<>(ResponseStatus.SUCCESS, null, holiday);
    }


}

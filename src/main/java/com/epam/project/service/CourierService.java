package com.epam.project.service;

import com.epam.project.model.dto.CourierDto;
import java.util.List;

public interface CourierService {
    List<CourierDto> findAll();

    CourierDto findById(Long id);

    CourierDto save(CourierDto courierDto);

    CourierDto update(CourierDto courierDto);

    void deleteById(Long id);
}

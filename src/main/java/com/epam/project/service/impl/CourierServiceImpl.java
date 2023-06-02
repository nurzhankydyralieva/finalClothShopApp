package com.epam.project.service.impl;

import com.epam.project.mapper.CourierMapper;
import com.epam.project.model.dto.CourierDto;
import com.epam.project.model.entitity.Courier;
import com.epam.project.repository.CourierRepository;
import com.epam.project.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    @Override
    public List<CourierDto> findAll() {
        return courierMapper.toDtos(courierRepository.findAll());
    }

    @Override
    public CourierDto findById(Long id) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The courier with id" + id + " not found"));
        return courierMapper.toDto(courier);
    }

    @Override
    public CourierDto save(CourierDto courierDto) {
        Courier courier = courierMapper.toEntity(courierDto);
        courier = courierRepository.save(courier);
        return courierMapper.toDto(courier);
    }

    @Override
    public CourierDto update(CourierDto courierDto) {
        if (!courierRepository.existsById(courierDto.getId())) {
            throw new RuntimeException("Courier is not available");
        }
        Courier courier = courierMapper.toEntity(courierDto);
        courier = courierRepository.save(courier);
        return courierMapper.toDto(courier);
    }

    @Override
    public void deleteById(Long id) {
        courierRepository.deleteById(id);
    }
}

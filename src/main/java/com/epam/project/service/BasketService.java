package com.epam.project.service;

import com.epam.project.model.dto.BasketDto;
import java.util.List;

public interface BasketService {
    List<BasketDto> findAll();

    BasketDto findById(Long id);

    BasketDto save(BasketDto basketDto);

    BasketDto update(BasketDto basketDto);

    void deleteById(Long id);
}
